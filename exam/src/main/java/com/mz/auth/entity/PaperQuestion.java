package com.mz.auth.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 一对多的关系，一套试卷有多道题
 * @author ufo
 */
@Data
public class PaperQuestion implements Serializable {
    /**
     * 试卷问题的id
     */
    private Long id;

    /**
     * 试卷的id
     */
    private Long paperId;
    /**
     * 问题id
     */
    private Long questionId;
    /**
     * 问题id集合，似乎没有用到
     * 查询的时候没有用到
     * 但是组卷传递的时候有用到
     */
    private List<Question> questionIdsList = new ArrayList<>();

}
