package com.mz.auth.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 8:57
 */
@Data
public class Role {
    /**
     * id 角色主键
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * sn 角色编号
     */
    private String sn;
    /**
     * 角色描述
     */
    private String desc;
    /**
     * 该角色对应权限
     */
    List<Permission> permissions = new ArrayList();
}
