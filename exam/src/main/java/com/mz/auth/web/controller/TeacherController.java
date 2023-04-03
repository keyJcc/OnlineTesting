package com.mz.auth.web.controller;

import com.mz.auth.entity.User;
import com.mz.auth.query.UserQuery;
import com.mz.auth.service.TeacherService;
import com.mz.auth.util.PageList;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "在线考试系统--教师管理界面")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * type==2代表教师
     * @param user
     * @return
     */
    @RequestMapping("/teacher/addTeacher")
    @ResponseBody
    public Long addTeacher(User user){
        System.out.println("教师添加。。。"+user);
        user.setType(2);
        teacherService.addTeacher(user);
        Long userId = user.getId();
        return userId;
    }

    @RequestMapping("/teacher/index")
    public String index(){
        return "views/teacher/teacher_list";
    }

    @GetMapping("/teacher/listpage")
    @ResponseBody
    public PageList listPage(UserQuery userQuery){
        userQuery.setType(2);
        return teacherService.listPage(userQuery);

    }


}
