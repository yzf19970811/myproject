package com.example.myproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.myproject.model.UserDTO;
import com.example.myproject.service.UserService;
import com.example.myproject.vo.UserVO;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * @author kyu.yzf
 * @date 2023/4/17 20:00
 */
@Controller
@RequestMapping("/main")
public class WebController extends HttpServlet {

    @Resource
    private UserService userService;

    @Resource
    private HttpClient httpClient;

    private String URL = "http://localhost:8080/main/user?&key1=1$key2=2&key3=3";

    @RequestMapping(value = "/http",method = RequestMethod.POST)
    @ResponseBody
    public String httpRequest(HttpServletRequest request,
                            @RequestBody Map<String, Object> paramsMap) {

        System.out.println("requestBody,paramsMap = " + JSONObject.toJSONString(paramsMap));

        Enumeration<String> headerNames = request.getHeaderNames();

        try {
            RequestBuilder requestBuilder = RequestBuilder.post(URL);

            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                requestBuilder.setHeader(headerName, request.getHeader(headerName));
                System.out.println("header KV = " + headerName + "::" + request.getHeader(headerName));
            }

            requestBuilder.addHeader("Accept", "application/json;charset=UTF-8");

            System.out.println("paramsKV" + JSONObject.toJSONString(request.getParameterMap()));

            requestBuilder.setEntity(new StringEntity(JSONObject.toJSONString(paramsMap), ContentType.APPLICATION_JSON));

            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String parameterName = parameterNames.nextElement();
                requestBuilder.addParameter(parameterName, request.getParameter(parameterName));
            }

            requestBuilder.setCharset(Charset.defaultCharset());
            HttpUriRequest httpUriRequest = requestBuilder.build();

            HttpResponse httpResponse = httpClient.execute(httpUriRequest);

            System.out.println("response entity = " + JSONObject.toJSONString(httpResponse.getEntity()));

            Charset defaultCharset = Charset.defaultCharset();
            System.out.println("defaultCharset = " + defaultCharset);

            String body = EntityUtils.toString(httpResponse.getEntity(), Charset.defaultCharset());

            System.out.println("body = " + body);

            return body;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello world!!!";
    }

    @RequestMapping
    public String index() {
        return "index";
    }

    @RequestMapping("/user/{userId}")
    @ResponseBody
    public UserVO queryUserInfo(@PathVariable("userId") String userId) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userService.queryByUserID(userId), userVO);
        return userVO;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public String addUserInfo(@RequestBody UserVO userVO) {
        System.out.println("userVO = " + userVO.toString());
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userVO, userDTO);
        userService.addUserInfo(userDTO);

        return JSONObject.toJSONString(userVO);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<UserVO> queryUserList() {
        List<UserVO> userVOList = new ArrayList<>();
        List<UserDTO> userDTOList = userService.queryUserList();
        userDTOList.forEach(userDTO -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userDTO, userVO);
            userVOList.add(userVO);
        });
        return userVOList;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String httpPost() {
        return "这是一串中文";
    }
}
