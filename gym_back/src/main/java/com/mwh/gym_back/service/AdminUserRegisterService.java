package com.mwh.gym_back.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mwh.gym_back.pojo.AdminUser;

public interface AdminUserRegisterService extends IService<AdminUser> {

    AdminUser check_admin_exist(String username);

    boolean adminregister(AdminUser adminUser);
}
