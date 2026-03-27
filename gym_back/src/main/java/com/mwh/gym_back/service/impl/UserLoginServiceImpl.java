package com.mwh.gym_back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mwh.gym_back.mapper.UserMapper;
import com.mwh.gym_back.pojo.User;
import com.mwh.gym_back.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLoginServiceImpl extends ServiceImpl<UserMapper, User> implements UserLoginService {
    @Autowired
    private UserMapper userMapper;

    public User check_user_password(String username,String password){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c_username",username).eq("c_password",password);
        return userMapper.selectOne(queryWrapper);
    }

    public User GetUserData(Integer id) {
        return userMapper.selectById(id);
    }
}
