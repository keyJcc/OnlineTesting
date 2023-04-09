package com.mz.auth.service.impl;

import com.mz.auth.entity.StuPaperQuestionVo;
import com.mz.auth.entity.StuScoreVo;
import com.mz.auth.mapper.ScoreMapper;
import com.mz.auth.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Override
    public StuPaperQuestionVo examDetailRecords(StuScoreVo stuScoreVo) {
        StuPaperQuestionVo stuPaperQuestionVo = scoreMapper.examDetailRecords(stuScoreVo);
        return stuPaperQuestionVo;
    }
}
