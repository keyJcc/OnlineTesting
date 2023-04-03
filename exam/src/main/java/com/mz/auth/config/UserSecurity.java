package com.mz.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

/**
 * @description: UserSecurity 重写实体User
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 9:03
 */
public class UserSecurity extends User {

    @Setter
    @Getter
    private com.mz.auth.entity.User loginUser;

    //把SpringSecurity里面的User用户 封装成自己的
    public UserSecurity(com.mz.auth.entity.User user, Set<GrantedAuthority> authoritySet){
        super(user.getUsername(),user.getPassword(),true,true,true,true,authoritySet);
        this.loginUser = user;
    }



}
