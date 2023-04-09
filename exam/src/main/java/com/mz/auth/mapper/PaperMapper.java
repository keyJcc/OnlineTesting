package com.mz.auth.mapper;

import com.mz.auth.entity.Paper;
import com.mz.auth.entity.PaperGenerateVo;
import com.mz.auth.entity.PaperQuestion;
import com.mz.auth.entity.TypeTotalVo;
import com.mz.auth.query.PaperQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 试卷的mapper
 * @author ufo
 */
@Mapper
public interface PaperMapper {


    /**
     * 获取试卷总数
     * @param paperQuery
     * @return
     */
    Long queryTotal(PaperQuery paperQuery);

    /**
     * 获得分页数据
     * @param paperQuery
     * @return
     */
    List<Paper> queryData(PaperQuery paperQuery);

    /**
     * 返回所有的试卷实体
     * @return
     */
    List<Paper> findAll();

    /**
     * 保存数据
     * @param paper
     * @return
     */
    @Insert("insert into\n" +
            "exam_paper(`name`,levelid,`STATUS`,startTime,endTime,createTime)\n" +
            "VALUES (#{name},#{levelid},#{status},#{startTime},#{endTime},#{createTime})")
    Integer addPaper(Paper paper);

    @Update("update \n" +
            "exam_paper\n" +
            "SET\n" +
            "`name`=#{name},\n" +
            "levelid = #{levelid},\n" +
            "startTime = #{startTime},\n" +
            "endTime = #{endTime}\n" +
            "WHERE id = #{id}")
    Integer editSavePaper(Paper paper);

    /**
     * 根据试卷id删除题目跟试卷的关系
     * @param paper
     * @return
     */
    @Delete("DELETE\n" +
            "FROM exam_paper_question\n" +
            "where paperId=#{id}")
    Integer deletePaperQuestion(Paper paper);


    /**
     * 批量插入试卷题目关系
     * @param params
     * @return 返回插入成功的数量
     */

    @Insert("<script>insert into exam_paper_question(paperId,questionId) " +
            "values" +
            "<foreach collection='list' item='item' separator=','>" +
            "(#{item.paperId},#{item.questionId})"+
            "</foreach>"+
            "</script>")
    Integer insertBatchPaperQuestion(List<Map> params);

    /**
     * 根据id删除试卷
     * @param paper
     * @return
     */
    @Delete("DELETE\n" +
            "FROM exam_paper\n" +
            "where id=#{id}")
    Integer deletePaper(Paper paper);

    @Select("SELECT id,paperId,questionId FROM exam_paper_question WHERE paperId=#{paperId}")
    List<PaperQuestion> queryQuestionByPaperId(Long paperId);

    /**
     * sql较为复杂，使用xml文件形式
     * @param paperId
     * @return
     */
    PaperGenerateVo previewPaper(Long paperId);

    /**
     * 查询各自类型对应的数量
     * @return
     */
    @Select("SELECT q_typeid,count(*) totalNum\n" +
            "FROM exam_questionbank\n" +
            "GROUP BY q_typeid")
    List<TypeTotalVo> queryTypeTotal();

    
}
