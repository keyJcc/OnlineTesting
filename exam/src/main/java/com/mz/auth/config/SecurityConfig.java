package com.mz.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created By 2021/8/20 20:04
 */
@Configuration
@EnableWebSecurity //表示开启SpringSecurity配置
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启方法的细粒度控制
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;


    /**
     * 配置类
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.authorizeRequests() //httpServletRequest的请求认证
        .antMatchers("/static/**","/goRegPage","/teacher/addTeacher","/file/uploadFile")//对静态资源的放行
        .permitAll()
         .anyRequest().authenticated()//除开放行的请求,其他请求就要经过认证
        .and().formLogin().and()//表示表单登录
        .csrf().disable() //关闭网络的攻击 xss
        .formLogin().loginPage("/login") //被拦截下来跳转的请求
        .loginProcessingUrl("/form")//出来form表单登录请求
        //.defaultSuccessUrl("/main")//登录成功进入的请求
         .successHandler(myAuthenctiationSuccessHandler)
        .failureUrl("/fail").permitAll();//登录失败请求

        //退出登录
        http.logout().logoutSuccessUrl("/login").invalidateHttpSession(true);




        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
                String header = req.getHeader("X-Requested-With");
                if("XMLHttpRequest".equals(header)){
                    resp.getWriter().println("{\"errorMsg\":\"不好意思你无权访问\"}");
                }else{
                    req.getRequestDispatcher("/error403").forward(req,resp);
                }
            }
        });

    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 配置内存的方式进行登录认证
         */
        //             auth.inMemoryAuthentication()
        //                .passwordEncoder(new BCryptPasswordEncoder())
        //                .withUser("admin")
        //                .password(new BCryptPasswordEncoder().encode("123"))
        //                .roles("USER");
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());

    }
}
