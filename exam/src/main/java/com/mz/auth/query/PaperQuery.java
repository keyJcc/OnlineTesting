package com.mz.auth.query;


import lombok.Data;

/**
 *用来接收前端传递的参数
 * @author ufo
 */
@Data
public class PaperQuery extends BaseQuery{
    /**
     * 试卷id
     */
    private Long id;

    /**
     * 试卷名称
     */
    private String name;
}
