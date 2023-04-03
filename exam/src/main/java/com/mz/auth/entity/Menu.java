package com.mz.auth.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Menu菜单实体类
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 20:50
 */
@Data
public class Menu implements Serializable {
    /**
     * 菜单主键
     */
    private Long id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单路径
     */
    private String url;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单父id
     */
    private Long pid;//父id
    /**
     * 菜单父对象
     */
    private Menu parent;//父对象
    /**
     * 子菜单
     */
    private List<Menu> childs = new ArrayList();
    /**
     * 权限
     */
    private Permission permission;
}
