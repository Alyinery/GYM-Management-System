package com.mwh.gym_back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mwh.gym_back.mapper.AdminMapper;
import com.mwh.gym_back.pojo.AdminUser;
import com.mwh.gym_back.service.AdminUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserLoginServiceImpl extends ServiceImpl<AdminMapper, AdminUser> implements AdminUserLoginService {

    @Autowired
    private AdminMapper adminMapper;

    public AdminUser check_adminuser_password(String username, String password) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a_username",username).eq("a_password",password);
        return adminMapper.selectOne(queryWrapper);
    }

    public AdminUser GetAdminUserData(Integer id) {
        return adminMapper.selectById(id);
    }
}
