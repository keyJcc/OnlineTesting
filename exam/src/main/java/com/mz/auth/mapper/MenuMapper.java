package com.mz.auth.mapper;

import com.mz.auth.entity.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description:
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 20:52
 */
@Mapper
public interface MenuMapper {

    /**
     * 根据userid查询菜单
     * @param userid
     * @return
     */
    List<Menu> listMenusByUserid(Long userid);


    /**
     * 查询所有的菜单
     * @return
     */
    @Select("select * from t_menu")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "url",property = "url"),
            @Result(column = "icon",property = "icon"),
            @Result(column = "pid",property = "pid"),
            @Result(column = "id",property = "permission",
                    one = @One(select = "com.mz.auth.mapper.PermissionMapper.getPermissionByMenuId")),
    })
    List<Menu> findAllMenus();

    /**
     * 保存顶级菜单
     * @param menu
     */
    @Insert("insert into t_menu(name,icon) values(#{name},#{icon})")
    void addTopMenu(Menu menu);


    /**
     * 保存子菜单 返回菜单id
     * @param menu
     */
    @Insert("insert into t_menu(name,url,icon,pid) values(#{name},#{url},#{icon},#{pid})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addSubMenu(Menu menu);

    /**
     * 根据id 查找菜单 和 子菜单
     * @param id
     * @return
     */
    @Select("select * from t_menu where id=#{id} or pid=#{id}")
    List<Menu> findMenuAndSubMenu(Long id);

    /**
     * 根据id删除菜单
     * @param id
     */
    @Delete("delete from t_menu where id=#{id}")
    void deleteMenuById(Long id);

    /**
     * 修改菜单
     * @param menu
     */
    @Update("update t_menu set name=#{name},url=#{url},icon=#{icon} where id=#{id}")
    void editMenu(Menu menu);


    /**
     * 根据菜单id 查询菜单
     */
    @Select("select * from t_menu where id=#{menuId}")
    Menu getMenuByMenuId(Long menuId);
}
