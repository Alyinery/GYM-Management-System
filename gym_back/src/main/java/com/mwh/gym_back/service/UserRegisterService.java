package com.mwh.gym_back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mwh.gym_back.pojo.User;

public interface UserRegisterService extends IService<User> {
    public User check_user_exist(String username);
    public boolean register(User user);
}
