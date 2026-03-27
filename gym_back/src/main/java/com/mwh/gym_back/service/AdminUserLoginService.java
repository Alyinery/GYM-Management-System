package com.mwh.gym_back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mwh.gym_back.pojo.AdminUser;
import com.mwh.gym_back.pojo.User;
import org.springframework.stereotype.Service;


public interface AdminUserLoginService extends IService<AdminUser> {
    public AdminUser check_adminuser_password(String username, String password);
    public AdminUser GetAdminUserData(Integer id);
}
