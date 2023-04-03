package com.mz.auth.web.controller;

import com.mz.auth.entity.DicTypeData;
import com.mz.auth.entity.Paper;
import com.mz.auth.entity.PaperQuestion;
import com.mz.auth.query.PaperQuery;
import com.mz.auth.service.DicService;
import com.mz.auth.service.PaperService;
import com.mz.auth.util.MzResult;
import com.mz.auth.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

/**
 * 使用spring Cache 进行优化
 * @author ufo
 */
@Controller
public class PaperController {
    @Autowired
    private PaperService paperService;

    @Autowired
    private DicService dicService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RedisTemplate redisTemplate;

    private String key = "question_list";

    /**
     * 获取试卷的分页数据
     * 使用cacheable注解，如果缓存中有数据的话从缓存中取，没有则执行方法
     * 此处似乎有点难使用spring cache注解：原因在于key难以确定，许多查询都会走这条路
     * 1.当没有id没有name时，返回全部，意味着这种情况需要有一个key
     * 2.同时也可根据name来进行模糊查询，这种情况需要根据名字有一个key
     *
     * 解决方法：使用默认策略来生成key，
     *      弊端：key不清晰，假如说有更新，不能及时清理缓存
     *          在默认策略中是以参数来作为生成key的依照，也就 意味着参数一样时有可能错误击中缓存
     * @param paperQuery
     * @return
     */
    @Cacheable(value = "paper",unless = "#result==null")
    @RequestMapping("/paper/listpage")
    @ResponseBody
    public PageList getPaperList(PaperQuery paperQuery){
        PageList pageList = paperService.listPage(paperQuery);
        return pageList;
    }

    /**
     * 进入试卷首页
     * @return
     */
    @RequestMapping("/paper/index")
    public String index(Model model){
        List<DicTypeData> levels = dicService.findLevels();
        model.addAttribute("levels",levels);
        return "views/paper/paper_list";
    }

    @RequestMapping("/paper/queryPaper")
    @ResponseBody
    public List<Paper> findAll(){
        List<Paper> all = paperService.findAll();
        return all;
    }

    @RequestMapping("/paper/savePaper")
    @ResponseBody
    public MzResult addPaper(Paper paper){

        try {
            paperService.addPaper(paper);
            return new MzResult();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error("新增失败，请重试！");
        }
    }

    @RequestMapping("/paper/editSavePaper")
    @ResponseBody
    public MzResult editPaper(Paper paper){
        try {
            paperService.editSavePaper(paper);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error("修改失败,请重试");
        }
    }

    @RequestMapping("/paper/deletePaper")
    @ResponseBody
    public MzResult deletePaper(Paper paper){

        try {
            paperService.deletePaper(paper);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error("删除失败，请重试！");
        }
    }

    /**
     * 跳转到试卷组题页面
     * @return
     */
    @RequestMapping("/paper/appendQuestion")
    public String questionList(){
        return "views/paper/paper_question";
    }

    /**
     * 根据试卷id查询对应题目，放在缓存中，key是试卷id
     * @param paperQuestion
     * @return
     */
    @RequestMapping("/paper/queryQuestionByPaperId")
    @ResponseBody
    @Cacheable(value = "paperQuestion",key = "#paperQuestion.paperId")
    public List<PaperQuestion> queryQuestionByPaperId(@RequestBody PaperQuestion paperQuestion){
        return paperService.queryQuestionByPaperId(paperQuestion.getPaperId());
    }

    /**
     *
     * @param paperQuestion 此处参数应该有paperId，以及QuestionIdsList
     * @return
     */
    @RequestMapping("/paper/diyPaperQuestion")
    @ResponseBody
    public MzResult diyPaperQuestion(@RequestBody PaperQuestion paperQuestion){
        try {
            paperService.diyPaperQuestion(paperQuestion);
            Set keys = redisTemplate.keys(key + "*");
            redisTemplate.delete(keys);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error("组卷失败，请重试！");
        }
    }


}
