package com.mz.auth.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ufo
 */
@Data
public class Student implements Serializable {
    /**
     * 主键
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

    private String tel;
    private String email;
    /**
     * 学号
     */
    private String stuNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//前台传递格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//后端接收格式
    private Date createTime;
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 登录验证码
     */
    private String usercode;
}
