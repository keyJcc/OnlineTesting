package com.mz.auth.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ufo
 */
@Data
public class ExamPaperVo {
    //试卷id
    private Long id;

    /**
     * 试卷问题集合
     */
    List<Question> questions = new ArrayList<>();

}
