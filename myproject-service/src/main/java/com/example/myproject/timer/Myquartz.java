package com.example.myproject.timer;

import com.example.myproject.redis.RedisClient;
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

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        System.out.println("hhhhh我是quartz");

        redisClient.add();
        String s = redisClient.get();
        System.out.println("s = " + s);
    }
}
