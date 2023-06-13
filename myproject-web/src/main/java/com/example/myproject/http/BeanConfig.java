package com.example.myproject.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.RequestContent;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kyu.yzf
 * @date 2023/6/8 20:26
 */
@Configuration
public class BeanConfig {

    @Bean
    public HttpClient httpClient() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().addInterceptorFirst(new RequestContent(true)).addInterceptorLast(new RequestContent(true)).build();
        return httpClient;
    }

    @Bean(name = "redissonClient")
    public RedissonClient redissonClient() {
        System.out.println("redisson client init");
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        return Redisson.create(config);
    }
}
