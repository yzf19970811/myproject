package com.example.myproject.timer;

import com.example.myproject.dubbo.DubboServiceConsumeClient;
import com.example.myproject.redis.RedisClient;
import com.example.myproject.rocketmq.RocketMqClient;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author kyu.yzf
 * @date 2023/5/14 16:37
 */
@Component
public class Myquartz extends QuartzJobBean {

    @Resource
    private RedisClient redisClient;

    @Resource
    private DubboServiceConsumeClient dubboServiceConsumeClient;

    @Resource
    private RocketMqClient rocketMqClient;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        System.out.println("hhhhh我是quartz");

        redisClient.add();
        String s = redisClient.get();
        System.out.println("s = " + s);

        String s1 = dubboServiceConsumeClient.sayHello();
        System.out.println("dubbo call result = " + s1);

        rocketMqClient.sendMsg();
        System.out.println("rocketmq已启动");
    }
}
