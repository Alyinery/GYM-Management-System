package com.mwh.gym_back.controller;

import com.mwh.gym_back.pojo.AdminUser;
import com.mwh.gym_back.pojo.User;
import com.mwh.gym_back.service.AdminUserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins ="*" ,maxAge = 3600)
@RestController
@RequestMapping("/gym")
public class AdminUserRegisterController {

    @Autowired
    private AdminUserRegisterService adminUserRegisterService;


    @GetMapping("/checkAdminUsername")
    private Result checkUsername(@RequestParam("username") String username) {
        System.out.println("UUUsername:"+username);
        AdminUser db_adminuser = adminUserRegisterService.check_admin_exist(username);
        if(db_adminuser != null) {
            System.out.println("管理员用户已存在");
            return new Result(null,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }


    @PostMapping("/adminRegister")
    private Result register(@RequestBody AdminUser adminUser) {
        System.out.println("adminuser:" + adminUser);
        boolean register = adminUserRegisterService.adminregister(adminUser);
        if (register == true) {
            System.out.println("注册成功");
//            用户名密码正确
            return new Result(null, Code.GET_OK, "success");
        } else {
//            用户名密码错误
            return new Result(null, Code.GET_ERR, "failure");
        }
    }
}
