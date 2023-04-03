package com.mz.auth.mapper;

import com.mz.auth.entity.Question;
import com.mz.auth.entity.QuestionXztOptions;
import com.mz.auth.query.QuestionQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    Long queryTotal();

    List<Question> queryData(QuestionQuery questionQuery);

    @Insert("INSERT INTO\n" +
            "exam_questionbank(questionTitle,questionAnswer,q_typeid,`STATUS`,createTime,grade,creatorId)\n" +
            "VALUES(#{questionTitle},#{questionAnswer},#{q_typeid},#{status},#{createTime},#{grade},#{creatorId})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer addQuestion(Question question);

    @Insert("INSERT INTO\n" +
            "exam_xzt_options(optionA,optionB,optionC,optionD,questionId)\n" +
            "VALUES(#{optionA},#{optionB},#{optionC},#{optionD},#{questionId})")
    Integer addXztOptions(QuestionXztOptions questionXztOptions);

    /**
     * 1，查询到题目数据，如果是选择题则需要再次查询选项答案
     * @param qid
     * @return
     */
    @Select("select * from exam_questionbank where id = #{qid}")
    @Results(value = {
            @Result(column = "id",property = "id",id = true),
            //此处的id作为参数给一对一查询，去执行查询方法，连结起来查询
            @Result(column = "id",property = "questionXztOptions",
                    one=@One(select = "getQuestionXztOptionsByQid")
            )
    })
    Question queryQuestionById(Long qid);

    /**
     * 根据题目id查询对应选项，是选择题则有数据，不是选择题则没有数据
     * 也就意味每一次题目查询都会进行题目跟选项的查询
     * @param qid
     * @return
     */
    @Select("select * from exam_xzt_options WHERE questionId = #{qid}")
    QuestionXztOptions getQuestionXztOptionsByQid(Long qid);


    @Update("UPDATE\n" +
            "exam_questionbank\n" +
            "SET\n" +
            "questionTitle=#{questionTitle},\n" +
            "questionAnswer=#{questionAnswer},\n" +
            "q_typeid=#{q_typeid},\n" +
            "grade=#{grade},\n" +
            "creatorId=#{creatorId}\n" +
            "WHERE\n" +
            "id = #{id}")
    Integer saveEditQuestion(Question question);

    @Delete("DELETE\n" +
            "FROM exam_xzt_options\n" +
            "WHERE questionId=#{qid}")
    Integer deleteXztOptions(Long qid);

    /**
     * 删除题目
     * @param id
     * @return
     */
    @Delete("delete from exam_questionbank where id=#{id}")
    Integer deleteQuestion(Long id);
}
