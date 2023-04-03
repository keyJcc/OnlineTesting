package com.mz.auth.service.impl;

import com.mz.auth.entity.Menu;
import com.mz.auth.entity.Permission;
import com.mz.auth.mapper.MenuMapper;
import com.mz.auth.mapper.PermissionMapper;
import com.mz.auth.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 21:06
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 根据用户id查询菜单
     * @param userid
     * @return
     */
    @Override
    public List<Menu> listMenusByUserid(Long userid) {
        return menuMapper.listMenusByUserid(userid);
    }

    /**
     * 查询所有的菜单
     * @return
     */
    @Override
    public List<Menu> findAllMenus() {
        return menuMapper.findAllMenus();
    }

    @Override
    public void addTopMenu(Menu menu) {
         menuMapper.addTopMenu(menu);
    }

    /**
     * 保存子菜单  测试子菜单页面
     *   t_menu
     *   t_permission
     * 事务：Transaction 表示都成功才成功，如果有一个失败，就回滚
     * @param menu
     */
    @Override
    @Transactional
    public void addSubMenu(Menu menu) {
        //保存菜单表
        menuMapper.addSubMenu(menu);
        //保存权限表
        Permission permission = menu.getPermission();
        permission.setPid(0L);
        permission.setMenuid(menu.getId());
        permissionMapper.addPermissionMenu(permission);
    }

    /**
     * 删除菜单
     *   (1) 先查询菜单 以及它的所有的子菜单
     *   (2) 如果子菜单有权限，删除想要子菜单的权限
     *   (3) 删除菜单
     * @param id
     */
    @Override
    @Transactional
    public void deleteMenu(Long id) {

       List<Menu> menus =  menuMapper.findMenuAndSubMenu(id);
       menus.forEach(menu->{
           //删除菜单对应的权限
           permissionMapper.deleteMenuPermission(id);
           //删除对应的菜单
           menuMapper.deleteMenuById(id);
       });

    }

    /**
     * 修改菜单
     * @param menu
     */
    @Override
    @Transactional
    public void editMenu(Menu menu) {
        menuMapper.editMenu(menu);
        //更新权限菜单名称
        permissionMapper.editPermissionByMenu(menu);
    }


}
