package com.test.demo.common.plugin;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MyBatisBaseMapper<T>  extends Mapper<T>, MySqlMapper<T> {

}
