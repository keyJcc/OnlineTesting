package com.mz.auth.service.impl;

import com.mz.auth.entity.Paper;
import com.mz.auth.entity.PaperGenerateVo;
import com.mz.auth.entity.PaperQuestion;
import com.mz.auth.entity.TypeTotalVo;
import com.mz.auth.mapper.PaperMapper;
import com.mz.auth.mapper.QuestionMapper;
import com.mz.auth.query.PaperQuery;
import com.mz.auth.service.PaperService;
import com.mz.auth.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private QuestionMapper questionMapper;
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

    @Override
    public PaperGenerateVo previewPaper(Long paperId) {
        PaperGenerateVo paperGenerateVo=paperMapper.previewPaper(paperId);
        return paperGenerateVo;
    }

    @Override
    public List<TypeTotalVo> queryTypeTotal() {
        return paperMapper.queryTypeTotal();
    }

    @Override
    @Transactional
    public Integer randomPaperQuestion(Map sendMap) {
        //保存最终的结果
        List<Long> resultIds = new ArrayList<>();
        Random random = new Random();
        //sendMap是传递过来的对应的题目数量
        Long xztNum=Long.valueOf((String)sendMap.get("xztNum"));
        Long tktNum=Long.valueOf((String)sendMap.get("tktNum"));
        Long pdtNum=Long.valueOf((String)sendMap.get("pdtNum"));
        Long jdtNum=Long.valueOf((String)sendMap.get("jdtNum"));

        //从数据库中获取对应题型的id集合，选择题
        List<Long> xztIds = questionMapper.queryQuestionIdsByType(1L);
        for(int i=0;i<xztNum;i++){
            //根据现有的id数量去一个随机数索引
            int rd = random.nextInt(xztIds.size());
            Long xztId = xztIds.get(rd);
            resultIds.add(xztId);
            //从现有集合中删除，已经选择过的
            xztIds.remove(xztId);
        }

        //从数据库中获取对应题型的id集合，填空题
        List<Long> tktIds = questionMapper.queryQuestionIdsByType(2L);
        for(int i=0;i<tktNum;i++){
            //根据现有的id数量去一个随机数索引
            int rd = random.nextInt(tktIds.size());
            Long tktId = tktIds.get(rd);
            resultIds.add(tktId);
            //从现有集合中删除已经选择过的
            tktIds.remove(tktId);
        }

        //从数据库中获取对应题型的id集合，选择题
        List<Long> pdtIds = questionMapper.queryQuestionIdsByType(3L);
        for(int i=0;i<pdtNum;i++){
            //根据现有的id数量去一个随机数索引
            int rd = random.nextInt(pdtIds.size());
            Long pdtId = pdtIds.get(rd);
            resultIds.add(pdtId);
            //从现有集合中删除，已经选择过的
            pdtIds.remove(pdtId);
        }

        //从数据库中获取对应题型的id集合，选择题
        List<Long> jdtIds = questionMapper.queryQuestionIdsByType(4L);
        for(int i=0;i<jdtNum;i++){
            //根据现有的id数量去一个随机数索引
            int rd = random.nextInt(jdtIds.size());
            Long jdtId = jdtIds.get(rd);
            resultIds.add(jdtId);
            //从现有集合中删除，已经选择过的
            jdtIds.remove(jdtId);
        }

        Long paperId = Long.valueOf((String)sendMap.get("paperId"));
        Paper paper = new Paper();
        paper.setId(paperId);
        //现在需要保存随机出来的ids，提前删除此时试卷中存在的题目
        paperMapper.deletePaperQuestion(paper);

        List<Map> params = resultIds.stream().map(id -> {
            Map map = new HashMap();
            map.put("paperId", paperId);
            map.put("questionId", id);
            return map;
        }).collect(Collectors.toList());

        Integer integer = paperMapper.insertBatchPaperQuestion(params);

        return integer;
    }
}
