package com.hezb.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

@Slf4j
public class RocketMQUtil {

    public static void syncSendMsg(DefaultMQProducer producer, Message message) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        SendResult result = producer.send(message);
        log.info("同步消息发送结果 {}", result);
    }

    public static void asyncSendMsg(DefaultMQProducer producer, Message message) throws RemotingException, InterruptedException, MQClientException {
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("异步消息发送成功 {}", sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.warn("异步消息发送异常 {}", throwable);
            }
        });
    }
}
