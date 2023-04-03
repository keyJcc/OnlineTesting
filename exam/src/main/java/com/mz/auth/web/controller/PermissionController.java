package com.mz.auth.web.controller;

import com.mz.auth.entity.Menu;
import com.mz.auth.entity.Permission;
import com.mz.auth.entity.Role;
import com.mz.auth.service.PermissionService;
import com.mz.auth.util.MzResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description: PermissionController
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/24 9:11
 */
@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 跳转权限列表页
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/permission/index")
    public String index(Long id, Model model){
        //查询所有的菜单
        List<Permission> permissions = permissionService.listAllPermission();
        model.addAttribute("permissions",permissions);
        model.addAttribute("menuid",id);
        return "views/permission/permission_list";
    }

    /**
     * 添加页面的按钮权限
     * @param permission
     * @return
     */
    @PostMapping("/permission/addBtnPermission")
    @ResponseBody
    public MzResult addBtnPermission(@RequestBody  Permission permission){
        try {
            permissionService.addBtnPermission(permission);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }

    /**
     * 修改页面的按钮权限
     * @param permission
     * @return
     */
    @PostMapping("/permission/editBtnPermission")
    @ResponseBody
    public MzResult editBtnPermission(@RequestBody  Permission permission){
        try {
            permissionService.editBtnPermission(permission);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }

    //deleteBtnPermission

    /**
     * 根据id删除按钮权限
     * @param id
     * @return
     */
    @PostMapping("/permission/deleteBtnPermission")
    @ResponseBody
    public MzResult deleteBtnPermission(Long id){
        try {
            permissionService.deleteBtnPermission(id);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }
}
