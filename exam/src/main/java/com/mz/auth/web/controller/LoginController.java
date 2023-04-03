package com.mz.auth.web.controller;

import com.mz.auth.entity.Menu;
import com.mz.auth.service.MenuService;
import com.mz.auth.util.CommonUtils;
import com.mz.auth.util.MzResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description:
 * @author: soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created By 2021/8/20 20:11
 */
@Controller
public class LoginController {

    @Autowired
    private MenuService menuService;

    /**
     * 拦截的时候跳转的登录页面
     */
    @RequestMapping("/login")
    public String login(){
        return "views/login";
    }
    /**
     * 登录成功请求
     */
    @RequestMapping("/index")
    public String main(HttpSession httpSession){
        /**
         * 查询登录用户对应的菜单
         */
        List<Menu> menus = menuService.
                listMenusByUserid(CommonUtils.getLoginUser().getId());
        if (menus != null) {
            httpSession.setAttribute("menuList",menus);
        }

        return "views/index";
    }
    /**
     * 登录失败请求
     * message
     */
    @RequestMapping("/fail")
    @ResponseBody
    public MzResult fail(){
        return new MzResult("用户名或者密码错误");
    }


    /**
     * 权限不足的跳转页面
     */
    @RequestMapping("/error403")
    public String error403(){
        return "error403";
    }

}
