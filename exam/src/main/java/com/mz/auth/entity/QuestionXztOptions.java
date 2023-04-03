package com.mz.auth.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ufo
 */
@Data
public class QuestionXztOptions implements Serializable {
    private Long id;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    /**
     * 问题主键
     */
    private Long questionId;
}
