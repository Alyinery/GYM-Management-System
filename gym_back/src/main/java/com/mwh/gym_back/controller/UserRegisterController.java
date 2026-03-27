package com.mwh.gym_back.controller;

import com.mwh.gym_back.pojo.User;
import com.mwh.gym_back.service.UserAttributeService;
import com.mwh.gym_back.service.UserRegisterService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins ="*" ,maxAge = 3600)
@RestController
@RequestMapping("/gym")
public class UserRegisterController {
    @Autowired
    private UserRegisterService userRegisterService;
    

    @GetMapping("/checkUsername")
    private Result checkUsername(@RequestParam("username") String username) {
        System.out.println("UUUsername:"+username);
        User db_user = userRegisterService.check_user_exist(username);
        if(db_user != null) {
            System.out.println("用户名已存在");
            return new Result(null,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }

    @PostMapping("/register")
    private Result register(@RequestBody User user) {
        System.out.println("user:" + user);

        // 对密码进行MD5加密
        String md5Password = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(md5Password); // 更新用户对象中的密码为加密后的密码

        System.out.println("Newuser\n"+user);

        boolean register = userRegisterService.register(user);

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
