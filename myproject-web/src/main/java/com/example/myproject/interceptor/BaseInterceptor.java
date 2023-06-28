package com.example.myproject.interceptor;

import org.redisson.Redisson;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * @author kyu.yzf
 * @date 2023/5/15 21:40
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {

//    @Resource
//    private RedissonClient redissonClient;

    private static RedissonClient redissonClient = null;

    static {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        redissonClient = Redisson.create(config);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        Arrays.stream(methodParameters).forEach(item -> System.out.println("item = " + item));

        Class<?> beanType = handlerMethod.getBeanType();
//        System.out.println("beanType = " + beanType);

        Method method = handlerMethod.getMethod();
        String name = method.getName();
//        System.out.println("name = " + name);


        Map pathVariableMapping = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String userId = (String) pathVariableMapping.get("userId");
//        System.out.println("userId = " + userId);

        boolean limitResult = checkLimit("mockKey3");
//        System.out.println("limitResult = " + limitResult);

        if (!limitResult) {
            PrintWriter writer = response.getWriter();
            writer.write("request too much!!!!!");
        }

        return limitResult;
    }

    private boolean checkLimit(String cacheKey) {
        System.out.println("限流逻辑命中");
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(cacheKey);
        System.out.println("rateLimiter = " + rateLimiter);
        if (!rateLimiter.isExists()) {
            rateLimiter.trySetRate(RateType.OVERALL, 20, 1, RateIntervalUnit.MINUTES);
        }
        boolean result = rateLimiter.tryAcquire(1);
        boolean delete = rateLimiter.delete();
        System.out.println("delete = " + delete);
        return result;
    }
}
