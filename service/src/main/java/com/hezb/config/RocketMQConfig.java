package com.hezb.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hezb.constant.UserMomentConstant;
import com.hezb.domain.UserMoment;
import com.hezb.pojo.FanUserInfoResponse;
import com.hezb.service.UserFollowService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class RocketMQConfig {

    @Value("${rocketmq.name.server.address}")
    private String nameServerAddress;

    private final UserFollowService userFollowService;
    private final RedisTemplate<String, String> redisTemplate;

    public RocketMQConfig(UserFollowService userFollowService, RedisTemplate<String, String> redisTemplate) {
        this.userFollowService = userFollowService;
        this.redisTemplate = redisTemplate;
    }

    @Bean("momentProducer")
    public DefaultMQProducer momentProducer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(UserMomentConstant.MOMENT_GROUP);
        producer.setNamesrvAddr(nameServerAddress);
        producer.start();
        return producer;
    }

    @Bean("momentConsumer")
    public DefaultMQPushConsumer momentConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(UserMomentConstant.MOMENT_GROUP);
        consumer.setNamesrvAddr(nameServerAddress);
        consumer.subscribe(UserMomentConstant.MOMENT_TOPIC, "*");
        consumer.registerMessageListener((MessageListenerConcurrently) (messageList, context) -> {
            try {
                messageList.stream().forEach(message -> {
                    log.info("RocketMQConfig#momentConsumer 当前消费消息 {} ", message);
                    UserMoment userMoment = JSONObject.toJavaObject(JSONObject.parseObject(new String(message.getBody())), UserMoment.class);
                    Long userId = userMoment.getUserId();
                    List<FanUserInfoResponse> fanUserList = userFollowService.getFanUsers(userId);
                    fanUserList.stream().forEach(fan -> {
                        String subscribeKey = "subscribed-" + fan.getUserId();
                        String subscribeListStr = redisTemplate.opsForValue().get(subscribeKey);
                        List<UserMoment> subscribeList;
                        if (StringUtils.isBlank(subscribeListStr)) {
                            subscribeList = new ArrayList<>();
                        } else {
                            subscribeList = JSONArray.parseArray(subscribeListStr, UserMoment.class);
                        }
                        subscribeList.add(userMoment);
                        redisTemplate.opsForValue().set(subscribeKey, JSONObject.toJSONString(subscribeList));
                    });
                });
            } catch (Exception exception) {
                log.warn("RocketMQConfig#momentConsumer 消息消费失败", exception);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        return consumer;
    }
}
