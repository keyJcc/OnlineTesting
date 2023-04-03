package com.mz.auth.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegController {
    /**
     * 跳转到注册页面
     * 新引入一个html文件后报500错误，thymeleaf解析错误
     * 排查后得知资源没有刷新，thymeleaf没有找到该页面（竟然不是报404！）
     * @return
     */
//    @GetMapping("/goRegPage")
    @RequestMapping("/goRegPage")
    public String register(){
        return "views/reg";
    }
}
