package com.example.myproject.controller;

import com.example.myproject.model.UserDTO;
import com.example.myproject.service.UserService;
import com.example.myproject.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index() {
        return "/static/index.html";
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
    public void addUserInfo(@RequestBody UserVO userVO) {
        System.out.println("userVO = " + userVO.toString());
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userVO, userDTO);
        userService.addUserInfo(userDTO);
    }
}
