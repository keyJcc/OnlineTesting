package com.mz.auth.mapper;

import com.mz.auth.entity.Role;
import com.mz.auth.query.RoleQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 14:52
 */
@Mapper
public interface RoleMapper {

    /**
     * 根据userid查询角色
     * @param userid
     * @return
     */
    @Select("SELECT r.*\n" +
            "from t_role r\n" +
            "join t_user_role ur on r.id = ur.roleid\n" +
            "where ur.userid = #{userid}")
    List<Role> listRolesByUserid(Long userid);

    /**
     * 查找总的条数
     * @param roleQuery
     * @return
     */
    Long queryTotal(RoleQuery roleQuery);

    /**
     * 分页的数据
     * @param roleQuery
     * @return
     */
    List queryData(RoleQuery roleQuery);

    /**
     * 保存角色
     * @param role
     */
    @Insert("insert into t_role(name,`desc`) values(#{name},#{desc})")
    void addRole(Role role);

    /**
     * 修改保存操作
     * @param role
     */
    @Update("update t_role set name=#{name},`desc`=#{desc} where id=#{id}")
    void editRole(Role role);

    /**
     * 删除角色
     * @param id
     */
    @Delete("delete from t_role where id=#{id}")
    void deleteRole(Long id);

    /**
     * 删除角色对应的权限
     * @param id
     */
    @Delete("delete from t_role_permission where roleid=#{id}")
    void deleteRolePermission(Long id);

    /**
     * 批量插入角色权限中间表
     * @param paramList
     */
    @Insert("<script>" +
            "insert into t_role_permission (roleid,permissionid) values" +
            "<foreach collection='list' item='item'  separator=',' >" +
            "(#{item.roleId},#{item.permissionId})" +
            "</foreach>"+
            "</script>")
    void saveRolePermission(List<Map> paramList);

    /**
     * 查询所有的角色
     * @return
     */
    @Select("select * from t_role")
    List<Role> findAllRole();
}