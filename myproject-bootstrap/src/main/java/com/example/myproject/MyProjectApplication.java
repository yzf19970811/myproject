package com.example.myproject;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.util.Objects;

/**
 * @author kyu.yzf
 * @date 2023/4/13 22:05
 */
@SpringBootApplication
//@EnableDubbo
@ImportResource(locations={"classpath*:spring/*.xml"})
public class MyProjectApplication {

    public static void main(String[] args) {

        try {
            ConfigurableApplicationContext context = SpringApplication.run(MyProjectApplication.class);
            String[] names = context.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println("name = " + name);
//        }
            RedissonClient redissonClient = (RedissonClient) context.getBean("redissonClient");
            if (!Objects.isNull(redissonClient)) {
                System.out.println("redisson client existed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
