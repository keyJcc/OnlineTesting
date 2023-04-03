package com.mz.auth.config;

import com.mz.auth.entity.Permission;
import com.mz.auth.entity.Role;
import com.mz.auth.entity.User;
import com.mz.auth.service.PermissionService;
import com.mz.auth.service.RoleService;
import com.mz.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 9:07
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if(user != null){
            Set<GrantedAuthority> authoritySet = new HashSet<>();
            List<Role> roles = roleService.listRolesByUserid(user.getId());
            for (Role role : roles) {
                authoritySet.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
            }
            //根据该用户的的信息 去查询对应的权限和角色 在交给springsecurity去管理
            //authoritySet.add(new SimpleGrantedAuthority("ROLE_管理员"));
            List<Permission> permissions = permissionService.listPermissionsByUserid(user.getId());
            for (Permission permission : permissions) {
                authoritySet.add(new SimpleGrantedAuthority(permission.getName()));
            }

            return new UserSecurity(user,authoritySet);
        }
        return null;
    }
}
