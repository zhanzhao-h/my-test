package com.test.demo.dao.mapper;

import com.test.demo.common.plugin.MyBatisBaseMapper;
import com.test.demo.model.pojo.TbWorkstation;

import java.util.List;

public interface TbWorkstationMapper extends MyBatisBaseMapper<TbWorkstation> {

    List<String> findFreeWorkstation();

}