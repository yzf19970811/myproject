package com.example.myproject.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.myproject.dubbo.DubboServiceConsumeClient;
import com.example.provider.DubboProvideService;
import org.springframework.stereotype.Service;

/**
 * @author kyu.yzf
 * @date 2023/5/19 23:07
 */
@Service
public class DubboServiceConsumeClientImpl implements DubboServiceConsumeClient {


    @Reference(version = "1.0.0")
    private DubboProvideService dubboProvideService;

    @Override
    public String sayHello() {
        return dubboProvideService.sayHello();
    }

}
