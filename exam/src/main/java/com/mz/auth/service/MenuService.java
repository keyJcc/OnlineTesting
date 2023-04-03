package com.mz.auth.service;

import com.mz.auth.entity.Menu;

import java.util.List;

/**
 * @description: MenuService
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 21:06
 */
public interface MenuService {
    /**
     * 根据userid查询对应菜单
     * @param userid
     * @return
     */
    List<Menu> listMenusByUserid(Long userid);


    /**
     * 查询所有的菜单
     * @return
     */
    List<Menu> findAllMenus();

    /**
     * 保存顶级菜单
     * @param menu
     */
    void addTopMenu(Menu menu);


    /**
     * 保存子菜单
     * @param menu
     */
    void addSubMenu(Menu menu);

    /**
     * 根据id删除菜单
     * @param id
     */
    void deleteMenu(Long id);

    /**
     * 修改菜单
     * @param menu
     */
    void editMenu(Menu menu);
}
