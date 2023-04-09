package com.mz.auth.mapper;

import com.mz.auth.entity.StuPaperQuestionVo;
import com.mz.auth.entity.StuScoreVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScoreMapper {
    /**
     * 查询试卷细节，xml文件实现
     * @param stuScoreVo
     * @return
     */
    StuPaperQuestionVo examDetailRecords(StuScoreVo stuScoreVo);
}
