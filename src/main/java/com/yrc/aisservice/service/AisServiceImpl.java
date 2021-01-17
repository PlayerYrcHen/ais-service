package com.yrc.aisservice.service;

import com.yrc.aisservice.entity.DataSeg;
import com.yrc.aisservice.mapper.AisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AisServiceImpl implements AisService {

    @Autowired
    private AisMapper aisMapper;

    @Override
    public List<DataSeg> findAll() {
        List<DataSeg> data = aisMapper.findAll();
        return data;
    }
}
