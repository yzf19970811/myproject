package com.example.myproject.interceptor.config;

import com.example.myproject.interceptor.BaseInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author kyu.yzf
 * @date 2023/5/15 21:57
 */
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BaseInterceptor()).addPathPatterns("/**");
    }
}
