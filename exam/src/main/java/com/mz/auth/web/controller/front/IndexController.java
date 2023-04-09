package com.mz.auth.web.controller.front;

import com.mz.auth.entity.Student;
import com.mz.auth.entity.User;
import com.mz.auth.service.StuService;
import com.mz.auth.util.CodeUtil;
import com.mz.auth.util.MzResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @author ufo
 */
@Controller
public class IndexController {

    /**
     * 跳转到前台首页
     * @return
     */
    @RequestMapping("/front/index")
    public String index(){
        return "views/front/frontIndex";
    }

    /**
     * 跳转到登录页
     * @return
     */
    @RequestMapping("/front/login")
    public String login(){
        return "views/front/loginIndex";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("/front/gotoRegPage")
    public String register(){
        return "views/front/regIndex";
    }



    @RequestMapping("/front/getCode")
    public void getCode(HttpSession session, HttpServletResponse resp){
        // 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();

        session.setAttribute("code", codeMap.get("code").toString());

        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);

        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = resp.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
