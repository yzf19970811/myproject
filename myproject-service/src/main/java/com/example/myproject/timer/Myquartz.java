package com.example.myproject.timer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author kyu.yzf
 * @date 2023/5/14 16:37
 */
public class Myquartz extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("hhhhh我是quartz");
    }
}
