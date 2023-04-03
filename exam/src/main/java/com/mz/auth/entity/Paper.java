package com.mz.auth.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Paper implements Serializable {
    /**
     * 试卷主键
     */
    private Long id;
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 试卷状态
     */
    private Integer status;
    /**
     * 测试开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//前端展示的时间格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//后台接受时间的格式
    private Date startTime;

    /**
     * 测试结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//前端展示的时间格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//后台接受时间的格式
    private Date endTime;

    /**
     * 试卷创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//前端展示的时间格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//后台接受时间的格式
    private Date createTime;

    /**
     *试卷等级
     */
    private Long levelid;


}
