package com.mz.auth.service;

import com.mz.auth.entity.Role;
import com.mz.auth.query.RoleQuery;
import com.mz.auth.util.PageList;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 14:54
 */
public interface RoleService {
    /**
     * 根据用户id 查询角色
     * @param userid
     * @return
     */
    List<Role> listRolesByUserid(Long userid);

    /**
     * 分页查询
     * @param roleQuery
     * @return
     */
    PageList listPage(RoleQuery roleQuery);

    /**
     * 保存角色
     * @param role
     */
    void addRole(Role role);

    /**
     * 修改保存角色
     * @param role
     */
    void editRole(Role role);

    /**
     * 根据id删除角色
     * @param id
     */
    void deleteRole(Long id);

    /**
     * 保存角色权限
     * @param paramMap
     */
    void saveRolePermission(Map paramMap);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAllRole();
}
