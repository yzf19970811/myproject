package com.example.myproject.timer;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kyu.yzf
 * @date 2023/5/14 16:33
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail(){
        //在这里工作明细要绑定一个job
        //现在这个工作就完成了这里必须要加上storeDurably,这个作用是当这个任务没有被执行也会一直存在保持持久化

        return JobBuilder.newJob(Myquartz.class).storeDurably().build();

    }
    @Bean
    public Trigger trigger(){

        //这里触发器要绑定一个工作明细JobDetail 同时要完成调度
        //这里要说明一下withSchedule是完成调度的下面这行代码是实现时间调度的要
        //要说明一下0 0 0 0 0 0，分别代表 秒 分 时 日 月 星期 其中日 和星期会有冲突通常我们都只配一个 另一个用？代替
        //ScheduleBuilder<? extends Trigger> schdule=CronScheduleBuilder.cronSchedule("0 0 0 0 0 0");
        //如CronScheduleBuilder.cronSchedule("0 0 0 1 2 ？")这就代表了2月的第一天0秒0分0时 我们还可以配*代表任意;
        //还可以如CronScheduleBuilder.cronSchedule("0/15 * * * * ？")代表每隔15秒执行一次
        ScheduleBuilder schdule= CronScheduleBuilder.cronSchedule("0/5 * * * * ?") ;
        //forJob就时绑定工作明细
        return TriggerBuilder.newTrigger().forJob(jobDetail()).withSchedule(schdule).build();
    }

}
