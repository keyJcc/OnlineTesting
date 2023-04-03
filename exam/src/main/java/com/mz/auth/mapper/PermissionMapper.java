package com.mz.auth.mapper;

import com.mz.auth.entity.Menu;
import com.mz.auth.entity.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description:
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 14:53
 */
@Mapper
public interface PermissionMapper {

    /**
     * 根据userid查询权限
     * @param userid
     * @return
     */
    @Select("select DISTINCT p.*\n" +
            "from t_permission p \n" +
            "join t_role_permission rp on p.id = rp.permissionid\n" +
            "join t_role r on r.id = rp.roleid\n" +
            "join t_user_role ur on r.id = ur.roleid\n" +
            "where ur.userid = #{userid}")
    List<Permission> listPermissionsByUserid(Long userid);

    /**
     * 根据菜单id查询对应的权限
     * @param menuid
     * @return
     */
    @Select("select * from t_permission where menuid=#{menuid}")
    Permission getPermissionByMenuId(Long menuid);


    @Insert("insert into t_permission(name,title,pid,menuid) values(#{name},#{title},#{pid},#{menuid})")
    void addPermissionMenu(Permission permission);

    /**
     * 表示根据菜单id 删除所有的权限
     * @param id
     */
    @Delete("delete from t_permission where menuid = #{id} \n" +
            "or pid = (select t.* from (select id from t_permission where menuid=#{id}) t)")
    void deleteMenuPermission(Long id);

    /**
     * 更改权限 通过menu
     * @param menu
     */
    @Update("update t_permission set title=#{name} where menuid=#{id}")
    void editPermissionByMenu(Menu menu);

    /**
     * 查询所有权限
     * @return
     */
    @Select("select * from t_permission")
    List<Permission> findAllPermission();
    /**
     * 查询所有权限 和对应菜单
     * @return
     */
    @Select("select * from t_permission")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "menuid",property = "menu",
                    one = @One(select = "com.mz.auth.mapper.MenuMapper.getMenuByMenuId")),
    })
    List<Permission> listAllPermission();

    /**
     * 添加按钮权限
     * @param permission
     */
    @Insert("insert into t_permission(name,title,pid) values (#{name},#{title},#{pid})")
    void addBtnPermission(Permission permission);

    /**
     * 修改页面按钮权限
     * @param permission
     */
    @Update("update t_permission set name=#{name},title=#{title} where id=#{id}")
    void editBtnPermission(Permission permission);

    /**
     * 根据权限id删除按钮权限
     * @param id
     */
    @Delete("delete from t_permission where id=#{id}")
    void deleteBtnPermission(Long id);
}
