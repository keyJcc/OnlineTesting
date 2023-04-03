package com.mz.auth.util;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author:soulcoder 自由如风
 * @email: 3393857689@qq.com
 * @date: created by 2021/8/21 22:44
 */
@Data
public class PageList implements Serializable {
    /**
     * 总的条数
     */
    private Long total;

    /**
     * 分页数据
     */
    private List rows = new ArrayList<>();

}
