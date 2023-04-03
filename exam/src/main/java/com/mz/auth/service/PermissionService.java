package com.mz.auth.service;

import com.mz.auth.entity.Permission;

import java.util.List;

/**
 * @description:
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 14:54
 */
public interface PermissionService {

    /**
     * 根据userid查询权限
     * @param userid
     * @return
     */
    List<Permission> listPermissionsByUserid(Long userid);

    /**
     * 查询所有的权限
     * @return
     */
    List<Permission> findAllPermission();

    /**
     * 查询所有权限和对应菜单
     * @return
     */
    List<Permission> listAllPermission();

    /**
     * 添加页面的按钮权限
     * @param permission
     */
    void addBtnPermission(Permission permission);

    /**
     * 修改页面权限按钮
     * @param permission
     */
    void editBtnPermission(Permission permission);

    /**
     * 根据id删除按钮权限
     * @param id
     */
    void deleteBtnPermission(Long id);
}
