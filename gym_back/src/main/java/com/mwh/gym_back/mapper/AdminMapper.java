package com.mwh.gym_back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mwh.gym_back.pojo.AdminUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper extends BaseMapper<AdminUser> {
}
