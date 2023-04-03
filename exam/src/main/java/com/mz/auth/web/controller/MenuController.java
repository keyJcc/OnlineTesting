package com.mz.auth.web.controller;

import com.mz.auth.entity.Menu;
import com.mz.auth.service.MenuService;
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
 * @description: MenuController
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/22 16:42
 */
@Controller
public class MenuController {


    @Autowired
    private MenuService menuService;

    /**
     * 菜单的列表页
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/menu/index")
    public String index(Long id, Model model){
        //查询所有的菜单
        List<Menu> menus = menuService.findAllMenus();
        model.addAttribute("menus",menus);
        model.addAttribute("menuid",id);
        return "views/menu/menu_list";
    }

    @PostMapping("/menu/addTopMenu")
    @ResponseBody
    public MzResult addTopMenu(Menu menu){
        try {
            menuService.addTopMenu(menu);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error("保存失败");
        }
    }

    /**
     * 保存子菜单
     * @param menu
     * @return
     */
    @PostMapping("/menu/addSubMenu")
    @ResponseBody
    public MzResult addSubMenu(@RequestBody Menu menu){
        try {
            menuService.addSubMenu(menu);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error("保存失败");
        }
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @PostMapping("/menu/deleteMenu")
    @ResponseBody
    public MzResult deleteMenu(Long id){
        try {
            menuService.deleteMenu(id);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }

    /**
     * 修改菜单
     * @param
     * @return
     */
    @PostMapping("/menu/editMenu")
    @ResponseBody
    public MzResult editMenu(@RequestBody Menu menu){
        try {
            menuService.editMenu(menu);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }














}
