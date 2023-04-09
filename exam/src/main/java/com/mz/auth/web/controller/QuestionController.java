package com.mz.auth.web.controller;

import com.mz.auth.entity.PaperGenerateVo;
import com.mz.auth.entity.Question;
import com.mz.auth.query.QuestionQuery;
import com.mz.auth.service.PaperService;
import com.mz.auth.service.QuestionService;
import com.mz.auth.util.MzResult;
import com.mz.auth.util.PageList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PaperService paperService;


    private String key = "question_list";
    /**
     * 加入redis优化
     * @param questionQuery
     * @return
     */
    @RequestMapping("/question/listpage")
    @ResponseBody
    public PageList questionList(QuestionQuery questionQuery){
        //这里是新建key值 question_list_#{offset}
        String listKey = key+"_"+questionQuery.getOffset();
        PageList pageList = null;
        pageList = (PageList) redisTemplate.opsForValue().get(listKey);
        if(pageList!=null){
            //如果说存在redis中直接返回
            return pageList;
        }

        //redis中不存在时去查询数据库,60分钟后过期
        pageList = questionService.queryData(questionQuery);
        redisTemplate.opsForValue().set(listKey,pageList,60, TimeUnit.MINUTES);
        return pageList;
    }

    /**
     * 编辑题目，参数为题目id
     * 修改题目后需要清除redis缓存
     * @param id
     * @return
     */
    @RequestMapping("/question/gotoEditQuestion/{id}")
    public String editQuestion(@PathVariable Long id, Model model){
        model.addAttribute("qid",id);
        return "views/question/question_edit";
    }

    @RequestMapping("/question/index")
    public String questionIndex(){
//        log.info("0");
        return "views/question/question_list";
    }

    @RequestMapping("/question/gotoAddQuestion")
    public String gotoAddQuestion(){
        return "views/question/question_add";
    }

    /**
     * 当前端将数据以json形式传输过来时，需要使用requestBody注解接收
     * @param question
     * @return
     */
    @RequestMapping("/question/addQuestion")
    @ResponseBody
    public MzResult addQuestion(@RequestBody Question question){
        try {
            questionService.addQuestion(question);
            Set keys = redisTemplate.keys(key + "*");
            redisTemplate.delete(keys);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error("新增题目失败！请重试");
        }
    }

    /**
     * 根据id查询题目
     * @param qid 是ajax请求发送的数据data:{'qid':qid}
     * @return
     */

    @RequestMapping("/question/queryQuestionByQid")
    @ResponseBody
    public Question queryQuestionByQid(Long qid) {
        Question question = questionService.queryQuestionById(qid);
        return question;
    }

    /**
     * 保存编辑过后的题目数据
     * @param question 前端传参
     * @return
     */
    @RequestMapping("/question/editQuestion")
    @ResponseBody
    public MzResult editQuestion(@RequestBody Question question){
        try {
            questionService.saveEditQuestion(question);
            Set keys = redisTemplate.keys(key + "*");
            redisTemplate.delete(keys);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error("保存失败，请重试!");
        }
    }

    @RequestMapping("/question/deleteQuestion")
    @ResponseBody
    public MzResult deleteQuestion(Long id){
        try {
            questionService.deleteQuestion(id);
            Set keys = redisTemplate.keys(key + "*");
            redisTemplate.delete(keys);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error("删除失败，请重试！");
        }
    }




}
