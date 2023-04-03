package com.mz.auth.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 8:54
 */
@Data
public class User implements Serializable{
    /**
     * id 用户的主键
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮件
     */
    private String email;
    /**
     * 电话号码
     */
    private String tel;
    /**
     * 处理时间格式
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 性别
     */
    private Boolean sex;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 类型
     */
    private Integer type; //type=1 是管理员  type=2是老师
    /**
     * 该用户对应的角色
     */
    private List<Role> roles = new ArrayList();//用户对应的角色集合

    public User() {
    }
}
