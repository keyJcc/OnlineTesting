package com.mz.auth.query;

import lombok.Data;

/**
 * @description: UserQuery 用来接收前台传递参数
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 22:42
 */
@Data
public class RoleQuery extends BaseQuery{
    /**
     * name 角色名称
     */
    private String name;

}
