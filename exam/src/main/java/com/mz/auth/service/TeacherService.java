package com.mz.auth.service;

import com.mz.auth.entity.User;
import com.mz.auth.query.UserQuery;
import com.mz.auth.util.PageList;
import org.springframework.stereotype.Service;

public interface TeacherService {
    /**
     * 添加教师
     * @param user
     * @return
     */
    Long addTeacher(User user);

    /**
     * 分页查询教师信息
     * @param userQuery
     * @return
     */
    PageList listPage(UserQuery userQuery);
}
