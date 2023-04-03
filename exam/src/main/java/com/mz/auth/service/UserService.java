package com.mz.auth.service;

import com.mz.auth.entity.User;
import com.mz.auth.query.UserQuery;
import com.mz.auth.util.PageList;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 9:01
 */

public interface UserService {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 查询所有的用户
     * @return
     */
    List<User> queryAll();

    /**
     * 分页查询数据
     * @param userQuery
     * @return
     */
    PageList listPage(UserQuery userQuery);


    /**
     * 保存用户方法
     * @param user
     * @return
     */
    Long addUser(User user);


    /**
     * 根据用户id 更新头像
     * @param user
     */
    void updateHeadImgByUser(User user);

    /**
     * 根据用户id 修改数据
     * @param user
     */
    void editUser(User user);


    /**
     * 根据id删除数据
     * @param id
     */
    void deleteUser(Long id);


    /**
     * 批量删除用
     * @param ids
     */
    void deleteBatchUser(Long[] ids);

    /**
     * 保存用户角色
     * @param paramMap
     */
    void saveUserRole(Map paramMap);
}
