package com.mz.auth.service;

import com.mz.auth.entity.StuPaperQuestionVo;
import com.mz.auth.entity.StuScoreVo;

public interface ScoreService {
    /**
     * 查询详细信息
     * @param stuScoreVo
     * @return
     */
    StuPaperQuestionVo examDetailRecords(StuScoreVo stuScoreVo);
}
