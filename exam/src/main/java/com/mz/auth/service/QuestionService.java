package com.mz.auth.service;

import com.mz.auth.entity.Question;
import com.mz.auth.query.QuestionQuery;
import com.mz.auth.util.PageList;

import java.util.List;

/**
 * @author ufo
 */
public interface QuestionService {
    /**
     * 获取题库总数
     * @return
     */
    Long queryTotal();

    /**
     * 获得题库分页数据
     * @param questionQuery
     * @return
     */
    PageList queryData(QuestionQuery questionQuery);

    /**
     * 新增题目数据
     * @param question
     * @return
     */
    Integer addQuestion(Question question);

    /**
     * 根据id获取题目
     * @param qid
     * @return
     */
    Question queryQuestionById(Long qid);

    /**
     * 保存
     * @param question
     * @return
     */
    Integer saveEditQuestion(Question question);

    /**
     * 删除题目
     * @param id
     * @return
     */
    Integer deleteQuestion(Long id);

}
