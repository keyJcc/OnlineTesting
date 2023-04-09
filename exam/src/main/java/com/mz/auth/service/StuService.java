package com.mz.auth.service;


import com.mz.auth.entity.StuAnswer;
import com.mz.auth.entity.StuScoreVo;
import com.mz.auth.entity.Student;
import com.mz.auth.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ufo
 */
public interface StuService {
    Integer saveStu(Student stu);

    /**
     * 根据用户名密码查找用户，可能重复
     * @param stu
     * @return
     */
    Student queryStuByNameAndPassword(Student stu);

    /**
     * 保存学生作答记录
     * @param answers
     * @return
     */
    Integer saveScoreDetail(List<StuAnswer> answers);

    /**
     * 查询学生总成绩
     * @param stuScoreVo
     * @return
     */
    StuScoreVo queryTotalScore(StuScoreVo stuScoreVo);

    /**
     * 根据id查找用户以及试卷名
     * @param stuScoreVo
     * @return
     */
//    @Select("")
//    StuScoreVo queryStudent(StuScoreVo stuScoreVo);
}
