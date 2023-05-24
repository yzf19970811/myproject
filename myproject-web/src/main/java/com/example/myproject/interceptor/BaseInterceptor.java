package com.example.myproject.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * @author kyu.yzf
 * @date 2023/5/15 21:40
 */
public class BaseInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String contentType = request.getContentType();
        System.out.println("contentType = " + contentType);

        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        StringBuffer requestURL = request.getRequestURL();
        System.out.println("requestURL = " + requestURL);

        String pathInfo = request.getPathInfo();
        System.out.println("pathInfo = " + pathInfo);

        String contextPath = request.getContextPath();
        System.out.println("contextPath = " + contextPath);

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        Arrays.stream(methodParameters).forEach(item -> System.out.println("item = " + item));

        Class<?> beanType = handlerMethod.getBeanType();
        System.out.println("beanType = " + beanType);

        Method method = handlerMethod.getMethod();
        String name = method.getName();
        System.out.println("name = " + name);


        Map pathVariableMapping = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String userId = (String) pathVariableMapping.get("userId");
        System.out.println("userId = " + userId);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
