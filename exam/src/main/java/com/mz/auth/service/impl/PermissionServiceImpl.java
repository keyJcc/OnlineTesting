package com.mz.auth.service.impl;

import com.mz.auth.entity.Permission;
import com.mz.auth.mapper.PermissionMapper;
import com.mz.auth.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 14:56
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> listPermissionsByUserid(Long userid) {
        return permissionMapper.listPermissionsByUserid(userid);
    }

    /**
     * 查询所有的权限
     * @return
     */
    @Override
    public List<Permission> findAllPermission() {
        return permissionMapper.findAllPermission();
    }

    /**
     * 查询所有的权限和对应的菜单
     * @return
     */
    @Override
    public List<Permission> listAllPermission() {
        return permissionMapper.listAllPermission();
    }

    /**
     * 添加页面按钮权限
     * @param permission
     */
    @Override
    public void addBtnPermission(Permission permission) {
        permissionMapper.addBtnPermission(permission);
    }

    /**
     * 修改页面 按钮权限功能
     * @param permission
     */
    @Override
    public void editBtnPermission(Permission permission) {
        permissionMapper.editBtnPermission(permission);
    }

    /**
     * 根据id删除按钮权限
     * @param id
     */
    @Override
    public void deleteBtnPermission(Long id) {
        permissionMapper.deleteBtnPermission(id);
    }
}
