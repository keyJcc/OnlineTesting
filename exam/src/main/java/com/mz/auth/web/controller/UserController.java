package com.mz.auth.web.controller;

import com.mz.auth.entity.Role;
import com.mz.auth.entity.User;
import com.mz.auth.query.UserQuery;
import com.mz.auth.service.RoleService;
import com.mz.auth.service.UserService;
import com.mz.auth.util.MzResult;
import com.mz.auth.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 15:04
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/user/list")
    @ResponseBody
    @PreAuthorize("hasRole('管理员11111111')")
    public List<User> queryAll(){
        return userService.queryAll();
    }


    /**
     * 跳转用户列表页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/user/index")
    public String index(Long id,Model model){
        model.addAttribute("menuid",id);
        //查询所有的角色
        List<Role> roles = roleService.findAllRole();
        model.addAttribute("roles",roles);
        return "views/user/user_list";
    }

    /**
     * 分页查询数据
     * @param userQuery
     * @return
     */
    @GetMapping("/user/listpage")
    @ResponseBody
    public PageList listPage(UserQuery userQuery){
        return userService.listPage(userQuery);
    }



    @PostMapping("/user/addUser")
    @ResponseBody
    public MzResult addUser(User user){
        try {
            Long userid = userService.addUser(user);
            return MzResult.ok().put("userid",userid);
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PostMapping("/user/editUser")
    @ResponseBody
    public MzResult editUser(User user){
        try {
             userService.editUser(user);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }

    /**
     * 删除单条数据
     * @param id
     * @return
     */
    @GetMapping("/user/deleteUser")
    @ResponseBody
    public MzResult deleteUser(Long id){
        try {
            userService.deleteUser(id);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }

    /**
     * 批量删除用户
     * ids[] 1,2,3
     * @param ids
     * @return
     */
    @PostMapping("/user/deleteBatchUser")
    @ResponseBody
    public MzResult deleteBatchUser(@RequestParam("ids[]") Long[] ids){
        try {
            userService.deleteBatchUser(ids);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }

    /**
     *  保存用户角色
     * @param paramMap  userId roleIds
     * @return
     */
    @PostMapping("/user/saveUserRole")
    @ResponseBody
    public MzResult saveUserRole(@RequestBody Map paramMap){
        try {
            userService.saveUserRole(paramMap);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }





}
