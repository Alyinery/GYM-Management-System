package com.mwh.gym_back.controller;

import com.mwh.gym_back.pojo.AdminUser;

import com.mwh.gym_back.pojo.AuthenticationResult;
import com.mwh.gym_back.service.AdminUserLoginService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@CrossOrigin(origins ="*" ,maxAge = 3600)
@RestController
@RequestMapping("/gym")
public class AdminUserLoginController {
    @Autowired
    private AdminUserLoginService adminUserLoginService;

    //打印日志
    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    @PostMapping("/adminlogin")
    private Result adminlogin(@RequestBody AdminUser adminUser) {
        System.out.println("Adminuser:" + adminUser);
        logger.info("The value of jwtSecret is: " + jwtSecret); // 记录日志，输出jwtSecret的值
        AdminUser db_admin_user = adminUserLoginService.check_adminuser_password(adminUser.getUsername(), adminUser.getPassword());
        if (db_admin_user != null) {
            // 用户名密码正确，生成token
            String token = Jwts.builder()
                    .setSubject(db_admin_user.getUsername()) // 设置用户名作为 token 的主题
                    .claim("userId", db_admin_user.getId()) // 添加自定义声明，例如用户 ID
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                    .signWith(SignatureAlgorithm.HS256, jwtSecret)
                    .compact();

//            用户名密码正确
            return new Result(new AuthenticationResult(token, db_admin_user), Code.GET_OK, "success");
        } else {
//            用户名密码错误
            return new Result(null, Code.GET_ERR, "failure");
        }
    }
}
