package com.mz.auth.query;

import lombok.Data;

/**
 * 前端可能传递的参数
 */
@Data
public class QuestionQuery extends BaseQuery{
    private Long id;
    private Integer q_typeid;
    private String questionTitle;
}
