package com.mz.auth.entity;

import lombok.Data;

/**
 * @description:
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 8:57
 */
@Data
public class Permission {
    /**
     * 权限的id主键
     */
    private Long id;
    /**
     * 权限的名称
     */
    private String name;
    /**
     * 权限的标题
     */
    private String title;
    /**
     * 权限的父id
     */
    private Long pid;
    /**
     * 权限对应菜单资源
     */
    private Long menuid;
    /**
     * 权限和菜单资源是一一对应关系
     */
    private Menu menu;
}
