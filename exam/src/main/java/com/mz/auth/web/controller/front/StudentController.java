package com.mz.auth.web.controller.front;

import com.mz.auth.entity.*;
import com.mz.auth.service.PaperService;
import com.mz.auth.service.ScoreService;
import com.mz.auth.service.StuService;
import com.mz.auth.util.MzResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ufo
 */
@Controller
public class StudentController {
    @Autowired
    private StuService stuService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private ScoreService scoreService;

    /**
     * 注册账号
     * @param stu
     * @return
     */
    @RequestMapping("/stu/regStu")
    @ResponseBody
    public MzResult doRegister(Student stu){
        try {
            Integer integer = stuService.saveStu(stu);
            if(integer==-1){
                return MzResult.error("学号重复，请检查学号");
            }
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error("注册失败，请重试！");
        }
    }

    ///stu/examPaper
    @RequestMapping("/stu/examPaper")
    @ResponseBody
    public MzResult saveStuAnswer(@RequestBody List<StuAnswer> answers){

        try {
            stuService.saveScoreDetail(answers);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error("提交失败，请重试！");
        }
    }

    /**
     * 登录验证
     * @param session
     * @param student
     * @return
     */

    ///stu/login
    @RequestMapping("/stu/login")
    @ResponseBody
    public MzResult doLogin(HttpSession session,Student student){
        try {
            //验证验证码是否一致
            String usercode = student.getUsercode();
            String code = (String) session.getAttribute("code");
            if(!usercode.equals(code)){
                return MzResult.error("验证码不一致");
            }
            //验证是否存在对应用户
            Student studentSearched = stuService.queryStuByNameAndPassword(student);
            if(studentSearched==null){
                return MzResult.error("该用户名不存在");
            }
            session.setAttribute("stuUser",studentSearched);
            return MzResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MzResult.error("登录失败，请重试！");
        }
    }

    ///stu/queryScorePage

    /**
     * 展示试卷，查询所有试卷并且返回
     * @return
     */
    @RequestMapping("/stu/queryScorePage")
    public String queryScorePage(Model model){
        List<Paper> allPaper = paperService.findAll();
        model.addAttribute("papers",allPaper);
        return "views/front/queryScoreIndex";
    }

    ///stu/queryScoreData
    @RequestMapping("/stu/queryScoreData")
    @ResponseBody
    public StuScoreVo queryScoreData(@RequestBody StuScoreVo stuScoreVo){
            StuScoreVo searchScore = stuService.queryTotalScore(stuScoreVo);
        if(searchScore==null){
            //说明没有参与此次考试
//            stuService.queryStudent(stuScoreVo);

            stuScoreVo.setTotalScore(0.0);
            return stuScoreVo;
        }
            return searchScore;
    }

    ///stu/examDetailRecords?paperId=10052&stuId=10
    @RequestMapping("/stu/examDetailRecords")
    public String examDetailRecords(StuScoreVo stuScoreVo,Model model){
        StuPaperQuestionVo stuPaperQuestionVo = scoreService.examDetailRecords(stuScoreVo);
        model.addAttribute("stuPaperQuestionVO",stuPaperQuestionVo);
        return "/views/front/examDetailIndex";
    }
}
