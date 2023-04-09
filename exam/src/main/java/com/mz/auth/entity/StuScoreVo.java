package com.mz.auth.entity;

import lombok.Data;

/**
 * @author ufo
 */
@Data
public class StuScoreVo {
    private Long stuId;
    private Long paperId;
    private String nickName;
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 总成绩
     */
    private Double totalScore;
}
