package com.mwh.gym_back.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mwh.gym_back.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserLoginService extends IService<User> {
    public User check_user_password(String username,String password);
    public User GetUserData(Integer id);
}
