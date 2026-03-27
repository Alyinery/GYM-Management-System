package com.mwh.gym_back.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mwh.gym_back.controller.Result;
import com.mwh.gym_back.pojo.UserAttribute;


public interface UserAttributeService extends IService<UserAttribute> {
    public UserAttribute getUserAttributeById(Integer id);

    public int modifyUserAttributeById(UserAttribute userAttribute);

}
