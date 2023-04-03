package com.mz.auth.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ufo
 */
@Data
public class DicTypeData implements Serializable {
    /**
     * 试卷等级id
     */
    private Long id;
    /**
     * 试卷等级名称，也就是难度
     * 也有文理科
     */
    private String name;

    /**
     * 标识区分是代表难度和文理科
     */
    private Long typeid;
}
