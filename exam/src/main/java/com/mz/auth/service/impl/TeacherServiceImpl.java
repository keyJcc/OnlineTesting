package com.mz.auth.service.impl;

import com.mz.auth.entity.User;
import com.mz.auth.mapper.UserMapper;
import com.mz.auth.query.UserQuery;
import com.mz.auth.service.TeacherService;
import com.mz.auth.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Long addTeacher(User user) {
        user.setCreateTime(new Date());
        //密码加密
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.addUser(user);

        return user.getId();
    }

    @Override
    public PageList listPage(UserQuery userQuery) {
        PageList pageList = new PageList();
        Long total = userMapper.queryTotal(userQuery);
        pageList.setTotal(total);
        List<User> users = userMapper.queryData(userQuery);
        pageList.setRows(users);
        return pageList;
    }
}
