package com.example.myproject.dubbo.impl;

import com.example.myproject.dubbo.DubboServiceConsumeClient;
import com.example.provider.impl.DubboProvideServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author kyu.yzf
 * @date 2023/5/19 23:07
 */
@Service
public class DubboServiceConsumeClientImpl implements DubboServiceConsumeClient {


    @Override
    public String sayHello() {
        return new DubboProvideServiceImpl().sayHello();
    }

}
