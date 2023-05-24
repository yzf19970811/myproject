package com.example.myproject.controller;

import com.example.myproject.service.UserService;
import com.example.myproject.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author kyu.yzf
 * @date 2023/4/17 20:00
 */
@Controller
@RequestMapping("/main")
public class WebController {

    @Resource
    private UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello world!!!";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index.html";
    }

    @RequestMapping("/user/{userId}")
    @ResponseBody
    public UserVO queryUserInfo(@PathVariable("userId") String userId) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userService.queryByUserID(userId), userVO);
        return userVO;
    }
}
