package com.yrc.aisservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yrc.aisservice.entity.DataSeg;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AisMapper extends BaseMapper<DataSeg> {

    @Select("select * from dataseg")
    List<DataSeg> findAll();
}
