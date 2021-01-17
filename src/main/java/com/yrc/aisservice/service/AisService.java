package com.yrc.aisservice.service;

import com.yrc.aisservice.entity.DataSeg;

import javax.xml.crypto.Data;
import java.util.List;

public interface AisService {
    public List<DataSeg> findAll();
}
