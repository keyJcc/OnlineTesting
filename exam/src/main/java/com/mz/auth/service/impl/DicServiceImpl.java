package com.mz.auth.service.impl;

import com.mz.auth.entity.DicTypeData;
import com.mz.auth.mapper.DicMapper;
import com.mz.auth.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DicServiceImpl implements DicService {
    @Autowired
    private DicMapper dicMapper;
    @Override
    public List<DicTypeData> findLevels() {
        List<DicTypeData> levels = dicMapper.findLevels();
        return levels;
    }
}
