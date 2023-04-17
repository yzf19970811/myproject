package com.example.myproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kyu.yzf
 * @date 2023/4/17 20:00
 */
@Controller
@RequestMapping("/main")
public class WebController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello world!!!";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index.html";
    }
}
