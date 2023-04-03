package com.mz.auth.service.impl;

import com.mz.auth.entity.Paper;
import com.mz.auth.entity.PaperQuestion;
import com.mz.auth.mapper.PaperMapper;
import com.mz.auth.query.PaperQuery;
import com.mz.auth.service.PaperService;
import com.mz.auth.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;
    @Override
    @ResponseBody
    public PageList listPage(PaperQuery paperQuery) {
        /**
         *怎么实现分页
         */
        PageList pageList = new PageList();
        Long total = paperMapper.queryTotal(paperQuery);
        pageList.setTotal(total);

        List<Paper> papers = paperMapper.queryData(paperQuery);
        pageList.setRows(papers);

        return pageList;
    }

    @Override
    public List<Paper> findAll() {
        List<Paper> all = paperMapper.findAll();
        return all;
    }

    /**
     * 此处处理，设置试卷状态以及创建时间
     * @param paper
     * @return
     */
    @Override
    public Integer addPaper(Paper paper) {

        paper.setStatus(0);
        paper.setCreateTime(new Date());
        Integer integer = paperMapper.addPaper(paper);
        return integer;
    }

    @Override
    public Integer editSavePaper(Paper paper) {
        Integer integer = paperMapper.editSavePaper(paper);
        return integer;
    }

    /**
     * 有试卷以及题目，题目不删除
     * 事务，需要删除试卷跟题目的中间表
     * @param paper
     * @return
     */
    @Override
    @Transactional
    public Integer deletePaper(Paper paper) {
        paperMapper.deletePaperQuestion(paper);
        Integer integer = paperMapper.deletePaper(paper);
        return integer;
    }

    @Override
    public List<PaperQuestion> queryQuestionByPaperId(Long paperId) {
        return paperMapper.queryQuestionByPaperId(paperId);
    }

    @Override
    @Transactional
    public Integer diyPaperQuestion(PaperQuestion paperQuestion) {

        //需要根据试卷id删除掉对应的题目关系，修改也是走这个路径
        Paper paper = new Paper();
        paper.setId(paperQuestion.getPaperId());
        paperMapper.deletePaperQuestion(paper);

        //再保存试卷组题
        List<Map> params = paperQuestion.getQuestionIdsList().stream().map(question -> {
            Map map = new HashMap();
            map.put("paperId", paperQuestion.getPaperId());
            map.put("questionId", question.getId());
            return map;
        }).collect(Collectors.toList());
        Integer integer = paperMapper.insertBatchPaperQuestion(params);
        return integer;
    }
}
