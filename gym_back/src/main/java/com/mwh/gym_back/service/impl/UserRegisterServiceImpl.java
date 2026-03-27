package com.mwh.gym_back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mwh.gym_back.mapper.UserMapper;
import com.mwh.gym_back.pojo.User;
import com.mwh.gym_back.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterServiceImpl extends ServiceImpl<UserMapper, User> implements UserRegisterService {
    @Autowired
    private UserMapper userMapper;
    public User check_user_exist(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c_username",username);
        return userMapper.selectOne(queryWrapper);
    }

    public boolean register(User user) {
        int insert = userMapper.insert(user);
        System.out.println(insert);
        return true;
    }
}
