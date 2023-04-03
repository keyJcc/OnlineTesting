package com.mz.auth.web.controller;

import com.mz.auth.entity.Permission;
import com.mz.auth.entity.Role;
import com.mz.auth.query.RoleQuery;
import com.mz.auth.query.UserQuery;
import com.mz.auth.service.PermissionService;
import com.mz.auth.service.RoleService;
import com.mz.auth.util.MzResult;
import com.mz.auth.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description: RoleController 角色控制器
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/23 14:01
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;
    /**
     * 跳转角色列表页方法
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/role/index")
    public String index(Long id, Model model){
        model.addAttribute("menuid",id);
        //查询所有权限
        List<Permission> permissions = permissionService.findAllPermission();
        model.addAttribute("permissions",permissions);

        return "views/role/role_list";
    }

    /**
     * 分页查询数据
     * @param roleQuery
     * @return
     */
    @GetMapping("/role/listpage")
    @ResponseBody
    public PageList listPage(RoleQuery roleQuery){
        return roleService.listPage(roleQuery);
    }

    /**
     * 保存角色
     * @param role
     * @return
     */
    @PostMapping("/role/addRole")
    @ResponseBody
    public MzResult addRole(Role role){
        try {
            roleService.addRole(role);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }

    /**
     * 修改保存角色
     * @param role
     * @return
     */
    @PostMapping("/role/editRole")
    @ResponseBody
    public MzResult editRole(Role role){
        try {
            roleService.editRole(role);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }

    @GetMapping("/role/deleteRole")
    @ResponseBody
    public MzResult deleteRole(Long id){
        try {
            roleService.deleteRole(id);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }

    /**
     *  保存角色对应权限
     * @param paramMap  roleId permissionIds
     * @return
     */
    @PostMapping("/role/saveRolePermission")
    @ResponseBody
    public MzResult saveRolePermission(@RequestBody Map paramMap){
        try {
            roleService.saveRolePermission(paramMap);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }











}
