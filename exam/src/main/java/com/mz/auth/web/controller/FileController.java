package com.mz.auth.web.controller;

import com.mz.auth.entity.User;
import com.mz.auth.service.UserService;
import com.mz.auth.util.MzResult;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @description:FileController 处理文件或者图片的类
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 17:31
 */
@Controller
public class FileController {

    @Autowired
    private UserService userService;

    @Value("${mz.upload.path}")
    private String uploadPath;

    /**
     * 上传方法
     *  (1)获取页面的上传的文件
     *  (2)构造一个新的文件
     *  (3)把获取的文件 写到新的文件里面
     */
    @PostMapping("/file/uploadFile")
    @ResponseBody
    public MzResult uploadFile(Long id,@RequestParam("file") MultipartFile file){
        try {
            if(file.isEmpty()){
                return MzResult.error("图片为空");
            }
            /**
             * 处理文件名  a.png  a.png
             * sdfasfdasdf  .png
             */
            String filename = file.getOriginalFilename();
            String suffixName = filename.substring(filename.lastIndexOf("."));
            String uuidStr = UUID.randomUUID().toString();
            String newFileName = uuidStr+suffixName; //sfasfsafdasfd.png

            //构造一个新的文件 D://upload/  +sdfsadfasfd.png
            File saveFile = new File(uploadPath, newFileName);
            if(!saveFile.getParentFile().exists()){
                saveFile.getParentFile().mkdirs();
            }
            file.transferTo(saveFile);
            //更新数据库的头像
            User user = new User();
            user.setHeadImg(newFileName);
            user.setId(id);
            userService.updateHeadImgByUser(user);
            return MzResult.ok();
        } catch (IOException e) {
            e.printStackTrace();
            return MzResult.error(e.getMessage());
        }
    }

    /**
     * 展示图片
     * @param image_name
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/showimage/{image_name}")
    public void showImage(@PathVariable("image_name") String image_name,
                            HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        //设置响应信息
        resp.setDateHeader("Expires", 0);
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
        resp.setHeader("Pragma", "no-cache");
        resp.setContentType("image/jpeg");
        //读取图片 oss 七牛云
        //如果兼容用centos 和 win
        File fileParent = new File(File.separator);
        File file = null;
        //获取操作系统的名称
        String os = System.getProperty("os.name");
        ServletOutputStream out = resp.getOutputStream();
        if(os.toLowerCase().startsWith("win")){
            //win
            file = new File(uploadPath+"\\"+image_name);
        }else{
            //linux和mac /   www/wwwroot/pic
            file = new File(fileParent,uploadPath.substring(1)+"/"+image_name);
        }
        IOUtils.copy(new FileInputStream(file),out);
        out.flush();

    }
}
