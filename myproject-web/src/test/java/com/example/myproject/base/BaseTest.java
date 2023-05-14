package com.example.myproject.base;


import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author kyu.yzf
 * @date 2023/5/14 16:22
 */
public class BaseTest {

    @Test
    public void testTimer() {
        //利用java的api Timer来完成一个定时任务
        Timer timer = new Timer();
        //其实这个技术也是基于java的线程去做的
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                System.out.println("这是一个定时任务");
            }
        };
        timer.schedule(task,0,2000);
    }

}
