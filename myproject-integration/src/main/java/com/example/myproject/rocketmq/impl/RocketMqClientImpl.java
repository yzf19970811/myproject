package com.example.myproject.rocketmq.impl;

import com.example.myproject.rocketmq.RocketMqClient;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kyu.yzf
 * @date 2023/5/28 22:47
 */
@Service
//@RocketMQMessageListener(consumerGroup = "group1",topic = "TestTopic")
public class RocketMqClientImpl implements RocketMqClient, RocketMQListener<String> {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void sendMsg() {
//        rocketMQTemplate.convertAndSend("TestTopic","这是一则发送过去的消息");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("监听到消息" + message);
    }
}
