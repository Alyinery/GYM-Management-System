package com.mwh.gym_back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mwh.gym_back.controller.Result;
import com.mwh.gym_back.mapper.UserAttributeMapper;
import com.mwh.gym_back.pojo.UserAttribute;
import com.mwh.gym_back.service.UserAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAttributeServiceImpl extends ServiceImpl<UserAttributeMapper, UserAttribute> implements UserAttributeService{
    @Autowired
    private UserAttributeMapper userAttributeMapper;

    public UserAttribute getUserAttributeById(Integer id) {
        return userAttributeMapper.selectById(id);
    }

    public int modifyUserAttributeById(UserAttribute userAttribute) {
        int i = userAttributeMapper.updateById(userAttribute);
        return i;
    }

}
