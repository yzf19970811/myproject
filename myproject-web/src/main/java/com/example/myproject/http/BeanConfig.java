package com.example.myproject.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.RequestContent;
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

}
