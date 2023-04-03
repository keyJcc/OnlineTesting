package com.mz.auth.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ufo
 */
@Data
public class QuestionType implements Serializable {
    /**
     * 问题类型id
     */
    private Long id;
    /**
     * 具体类型
     */
    private String name;
    /**
     * 描述
     */
    private String desc;
    /**
     * 类型索引
     */
    private String typeNum;
}
