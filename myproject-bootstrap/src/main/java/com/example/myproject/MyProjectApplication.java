package com.example.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author kyu.yzf
 * @date 2023/4/13 22:05
 */
@SpringBootApplication
@ImportResource(locations="classpath:spring/*.xml")
public class MyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyProjectApplication.class);
    }
}
