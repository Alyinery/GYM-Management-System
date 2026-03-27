package com.mwh.gym_back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mwh.gym_back.mapper.AdminMapper;
import com.mwh.gym_back.mapper.UserMapper;
import com.mwh.gym_back.pojo.AdminUser;
import com.mwh.gym_back.pojo.User;
import com.mwh.gym_back.service.AdminUserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserRegisterServiceImpl extends ServiceImpl<AdminMapper, AdminUser> implements AdminUserRegisterService {

    @Autowired
    private AdminMapper adminMapper;
    public AdminUser check_admin_exist(String username) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a_username",username);
        return adminMapper.selectOne(queryWrapper);
    }

    public boolean adminregister(AdminUser adminUser) {
        int insert = adminMapper.insert(adminUser);
        System.out.println(insert);
        return true;
    }


}
