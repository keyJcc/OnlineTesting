package com.mz.auth.entity;

import lombok.Data;

/**
 * @author ufo
 */
@Data
public class StuAnswer {
    private Long id;
    private Long stuId;
    /**
     * 试卷的id，前端传递过来时是字符串
     */
    private Long paperId;

    private Long q_typeid;
    private Long questionId;
    private String questionAnswer;
    private Long questionScore;
    private String questionTitle;
    private String correntAnswer;
    private Long correntScore;

}
