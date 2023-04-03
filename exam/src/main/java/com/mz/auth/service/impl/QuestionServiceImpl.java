package com.mz.auth.service.impl;

import com.mz.auth.entity.Question;
import com.mz.auth.entity.QuestionXztOptions;
import com.mz.auth.mapper.QuestionMapper;
import com.mz.auth.query.QuestionQuery;
import com.mz.auth.service.QuestionService;
import com.mz.auth.util.CommonUtils;
import com.mz.auth.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ufo
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public Long queryTotal() {
        Long aLong = questionMapper.queryTotal();
        return aLong;
    }

    @Override
    public PageList queryData(QuestionQuery questionQuery) {

        PageList pageList = new PageList();
        pageList.setTotal(questionMapper.queryTotal());
        pageList.setRows(questionMapper.queryData(questionQuery));

        return pageList;
    }

    @Override
    public Integer addQuestion(Question question) {

        question.setCreateTime(new Date());
        question.setStatus(0);//默认启用，默认为0
        question.setCreatorId(CommonUtils.getLoginUser().getId());
        Integer ques = questionMapper.addQuestion(question);

        Integer ops = -1;
        if(question.getQuestionXztOptions()!=null){
            question.getQuestionXztOptions().setQuestionId(question.getId());
            ops = questionMapper.addXztOptions(question.getQuestionXztOptions());
        }
        return ques+ops;
    }

    @Override
    public Question queryQuestionById(Long qid) {
        Question question = questionMapper.queryQuestionById(qid);
        return question;
    }

    @Override
    public Integer saveEditQuestion(Question question) {
        //需要先修改创建人id为最近修改人
        question.setCreatorId(CommonUtils.getLoginUser().getId());
        //更新题目
        Integer integer = questionMapper.saveEditQuestion(question);

        //修改选择题选项（删除再新增）
        Integer integer1 = questionMapper.deleteXztOptions(question.getId());
        if (question.getQ_typeid()==1L) {
            //将选择题选项与题号联系起来
            QuestionXztOptions questionXztOptions = question.getQuestionXztOptions();
            questionXztOptions.setQuestionId(question.getId());

            questionMapper.addXztOptions(questionXztOptions);
        }
        return integer+integer1;
    }

    @Override
    public Integer deleteQuestion(Long id) {
        //删除选择题选项先
        Integer integer = questionMapper.deleteXztOptions(id);
        Integer integer1 = questionMapper.deleteQuestion(id);
        return integer+integer1;
    }
}
