package com.mz.auth.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ufo
 */
@Data
public class StuPaperQuestionVo {
    private Long id;
    private String name;
    /**
     * 问题的集合，存有正确答案和学生答案
     */
    private List<StuQuestionQueryVo> stuQuestionsList = new ArrayList<>();
}
