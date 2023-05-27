package com.example.myproject.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.example.myproject.service.MyDubboProvideService;

/**
 * @author kyu.yzf
 * @date 2023/5/27 10:28
 */
@Service(version = "1.0.0")
public class MyDubboProvideServiceImpl implements MyDubboProvideService {

    @Override
    public String sayHello() {

        RpcContext context = RpcContext.getContext();
        String localHostName = context.getLocalHostName();
        String methodName = context.getMethodName();

        System.out.println("localHostName = " + localHostName);
        System.out.println("methodName = " + methodName);

        return "hello!!!!";
    }
}
