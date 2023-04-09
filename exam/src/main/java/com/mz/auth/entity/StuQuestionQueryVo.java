package com.mz.auth.entity;

import lombok.Data;

/**
 * @author ufo
 */
@Data
public class StuQuestionQueryVo {

    private Long questionId;
    private String questionTitle;//问题题目
    private String stuAnswer;//学生答案
    private String correntAnswer;//正确答案
    private Long q_typeid;//问题类型id
    private Integer grade;//问题分数
    private Integer correntScore;//正确得分
    private QuestionXztOptions questionXztOptions;
}
