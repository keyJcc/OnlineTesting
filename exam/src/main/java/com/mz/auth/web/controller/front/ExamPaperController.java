package com.mz.auth.web.controller.front;

import com.mz.auth.entity.ExamPaperVo;
import com.mz.auth.entity.PaperGenerateVo;
import com.mz.auth.query.PaperQuery;
import com.mz.auth.service.PaperService;
import com.mz.auth.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ufo
 */
@Controller
public class ExamPaperController {
    @Autowired
    private PaperService paperService;
    @RequestMapping("/paperindex/listpage")
    @ResponseBody
    @Cacheable(value = "stu_paper")
    public PageList paperIndex(PaperQuery paperQuery){
        PageList pageList = paperService.listPage(paperQuery);
        return pageList;
    }


    ///exam/popPaper/10052

    @RequestMapping("/exam/popPaper/{paperId}")
    public String examPaper(@PathVariable("paperId") Long paperId, Model model){
//        ExamPaperVo examPaperVo =
        PaperGenerateVo paperGenerateVo = paperService.previewPaper(paperId);
        model.addAttribute("examPapersVO",paperGenerateVo);
        return "views/front/examPaper";
    }

    ///stu/examPaper
}
