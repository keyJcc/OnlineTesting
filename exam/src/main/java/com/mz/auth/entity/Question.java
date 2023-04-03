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
public class Question implements Serializable {
    /**
     * 主键id，主键回填在新增方法中使用options注解
     */
    private Long id;
    /**
     * 问题描述
     */
    private String questionTitle;
    /**
     * 问题答案
     */
    private String questionAnswer;
    /**
     * 问题类型
     */
    private Integer q_typeid;
    /**
     * 问题状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//前端展示的时间格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//后台接受时间的格式
    private Date createTime;
    /**
     * 分值
     */
    private Integer grade;
    /**
     * 创建者编号
     */
    private Long creatorId;


    /**
     * 为了在前端显示，需要增加一些属性，来方便前端取值，因为不可能传输多个对象过去
     */
    private QuestionType questionType;
    private QuestionXztOptions questionXztOptions;
    private User user;


}
