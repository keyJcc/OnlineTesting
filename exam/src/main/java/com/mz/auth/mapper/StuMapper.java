package com.mz.auth.mapper;

import com.mz.auth.entity.StuAnswer;
import com.mz.auth.entity.StuScoreVo;
import com.mz.auth.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ufo
 */
@Mapper
public interface StuMapper {
    @Select("SELECT count(*) from t_student WHERE stuNum=#{stuNum}")
    Integer isExistStu(String stuNum);

    @Insert("INSERT INTO t_student(username,`password`,tel,email,stuNum,createTime,nickName) \n" +
            "VALUES(#{username},#{password},#{tel},#{email},#{stuNum},#{createTime},#{nickName})")
    Integer addStu(Student stu);

    /**
     * 根据用户名查找该学生，没有考虑用户名重复
     * @param username
     * @return
     */
    @Select("SELECT id,username,`password`,tel,email,stuNum,createTime,nickName\n" +
            "FROM t_student\n" +
            "WHERE username=#{username}")
    Student queryStudentByName(String username);

    @Insert("<script>insert into exam_scoredetail(stuId,paperId,questionId,questionTitle,q_typeid,questionAnswer,questionScore,correntAnswer,correntScore) " +
            "values" +
            "<foreach collection='list' item='item' separator=','>" +
            "(#{item.stuId},#{item.paperId},#{item.questionId},#{item.questionTitle},#{item.q_typeid},#{item.questionAnswer},#{item.questionScore},#{item.correntAnswer},#{item.correntScore})"+
            "</foreach>"+
            "</script>")
    Integer saveScoreDetail(List<StuAnswer> answers);

    /**
     * 查询学生对应试卷总成绩
     * @param stuScoreVo
     * @return
     */
    StuScoreVo queryTotalScore(StuScoreVo stuScoreVo);
}
