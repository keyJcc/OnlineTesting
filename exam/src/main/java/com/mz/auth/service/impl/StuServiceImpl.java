package com.mz.auth.service.impl;

import com.mz.auth.entity.StuAnswer;
import com.mz.auth.entity.StuScoreVo;
import com.mz.auth.entity.Student;
import com.mz.auth.mapper.StuMapper;
import com.mz.auth.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author ufo
 */
@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private StuMapper stuMapper;
    @Override
    public Integer saveStu(Student stu) {
        stu.setCreateTime(new Date());
        //判断学号是否存在
        Integer existStu = stuMapper.isExistStu(stu.getStuNum());
        if(existStu>0){
            //学号已存在
            return -1;
        }
        //密码加密
        stu.setPassword(new BCryptPasswordEncoder().encode(stu.getPassword()));
        //保存学生
        Integer integer = stuMapper.addStu(stu);
        return integer;
    }

    @Override
    public Student queryStuByNameAndPassword(Student stu) {
        //用户名校验，密码校验
        //根据用户名获得密码，进行密码校验
//        String password = stuMapper.queryStudentByName(stu.getUsername());
        Student studentInRecord = stuMapper.queryStudentByName(stu.getUsername());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean matches = bCryptPasswordEncoder.matches(stu.getPassword(), studentInRecord.getPassword());
        if(matches){
            return studentInRecord;
        }
        else {
            return null;
        }
    }

    /**
     * 保存作答答案，似乎不用保存作答记录，这本身就可以作为作答记录（？）
     * @param answers
     * @return
     */
    @Override
    @Transactional
    public Integer saveScoreDetail(List<StuAnswer> answers) {
        Integer integer = stuMapper.saveScoreDetail(answers);

        return integer;
    }

    @Override
    public StuScoreVo queryTotalScore(StuScoreVo stuScoreVo) {
        StuScoreVo stuScoreVo1 = stuMapper.queryTotalScore(stuScoreVo);
        return stuScoreVo1;
    }
}
