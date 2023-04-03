package com.mz.auth.service;

import com.mz.auth.entity.DicTypeData;

import java.util.List;

/**
 * @author ufo
 */
public interface DicService {
    /**
     * 返回试卷等级
     * @return
     */
    List<DicTypeData> findLevels();
}
