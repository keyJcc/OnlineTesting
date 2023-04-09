package com.mz.auth.entity;

import lombok.Data;

/**
 * @author ufo
 */
@Data
public class TypeTotalVo {
    /**
     * 题目类型
     */
    private Long q_typeid;
    /**
     * 具体类型题目数量
     */
    private Long totalNum;
}
