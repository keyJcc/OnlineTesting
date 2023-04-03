package com.mz.auth.query;

import lombok.Data;

/**
 * @description: UserQuery 用来接收前台传递参数
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 22:42
 */
@Data
public class UserQuery extends BaseQuery{


    /**
     * username 用户名
     */
    private String username;
    /**
     * email 邮件
     */
    private String email;

    /**
     * 用户类型
     */
    private Integer type;
}
