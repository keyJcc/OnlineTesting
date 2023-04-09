package com.mz.auth.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 试卷复合类，前端交流
 * @author ufo
 */
@Data
public class PaperGenerateVo implements Serializable {
    /**
     * 试卷id
     */
    private Long id;
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 试卷开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//前端展示的时间格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//后台接受时间的格式
    private Date startTime;

    /**
     * 试卷结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//前端展示的时间格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//后台接受时间的格式
    private Date endTime;
    /**
     * 试卷包含的问题实体
     */
    List<Question> questions = new ArrayList<>();
}
