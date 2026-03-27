package com.mwh.gym_back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mwh.gym_back.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface UserMapper extends BaseMapper<User> {


}
