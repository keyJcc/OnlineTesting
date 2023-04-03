package com.mz.auth.service;

import com.mz.auth.entity.Paper;
import com.mz.auth.entity.PaperQuestion;
import com.mz.auth.query.PaperQuery;
import com.mz.auth.util.PageList;

import java.util.List;

public interface PaperService {

    /**
     * 获取分页数据
     *@param paperQuery
     * @return
     */
    PageList listPage(PaperQuery paperQuery);

    /**
     * 获取所有数据
     * @return
     */
    List<Paper> findAll();

    /**
     * 保存试卷
     * @param paper
     * @return
     */
    Integer addPaper(Paper paper);

    /**
     * 编辑后保存
     * @param paper
     * @return
     */
    Integer editSavePaper(Paper paper);

    /**
     * 删除试卷
     * @param paper
     * @return
     */
    Integer deletePaper(Paper paper);

    /**
     * 根据试卷id查询对应问题
     * @param paperId
     * @return
     */
    List<PaperQuestion> queryQuestionByPaperId(Long paperId);

    /**
     * 试卷组题
     * @param paperQuestion paperId和questionIdsList是一对多的
     * @return
     */
    Integer diyPaperQuestion(PaperQuestion paperQuestion);
}
