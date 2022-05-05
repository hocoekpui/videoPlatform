package com.hezb.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hezb.constant.UserMomentConstant;
import com.hezb.domain.UserMoment;
import com.hezb.mapper.UserMomentMapper;
import com.hezb.model.UserMomentReleaseRequest;
import com.hezb.util.RocketMQUtil;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
public class UserMomentService {

    private final UserMomentMapper userMomentMapper;
    private final ApplicationContext applicationContext;
    private final RedisTemplate<String, String> redisTemplate;

    public UserMomentService(UserMomentMapper userMomentMapper, ApplicationContext applicationContext, RedisTemplate<String, String> redisTemplate) {
        this.userMomentMapper = userMomentMapper;
        this.applicationContext = applicationContext;
        this.redisTemplate = redisTemplate;
    }

    @Transactional
    public Long releaseMoment(Long userId, UserMomentReleaseRequest request) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        UserMoment userMoment = UserMoment.builder().userId(userId).type(request.getType()).contentId(request.getContentId()).createTime(new Date()).build();
        userMomentMapper.insert(userMoment);
        DefaultMQProducer momentProducer = (DefaultMQProducer) applicationContext.getBean("momentProducer");
        Message message = new Message(UserMomentConstant.MOMENT_TOPIC, JSONObject.toJSONString(userMoment).getBytes(StandardCharsets.UTF_8));
        RocketMQUtil.syncSendMsg(momentProducer, message);
        return userMoment.getUserId();
    }

    public List<UserMoment> getSubscribedMoment(Long userId) {
        String str = redisTemplate.opsForValue().get("subscribed-" + userId);
        return JSONArray.parseArray(str , UserMoment.class);
    }
}
