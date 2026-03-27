package com.mwh.gym_back.controller;


import com.mwh.gym_back.pojo.AuthenticationResult;
import com.mwh.gym_back.pojo.User;
import com.mwh.gym_back.service.UserLoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


@CrossOrigin(origins ="*" ,maxAge = 3600)
@RestController
@RequestMapping("/gym")
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

    //打印日志
    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    @PostMapping("/login")
    private Result login(@RequestBody User user) {
        System.out.println("user:" + user);
        logger.info("The value of jwtSecret is: " + jwtSecret); // 记录日志，输出jwtSecret的值

        // 对密码进行MD5加密
        String md5Password = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(md5Password); // 更新用户对象中的密码为加密后的密码


        User db_user = userLoginService.check_user_password(user.getUsername(), user.getPassword());
        if (db_user != null) {
//            String token = JwtTokenUtil.generateTokenForUser(db_user); // 假设这里是生成token的逻辑函数
//            db_user.setToken(token); // 将token设置到用户对象中

            // 用户名密码正确，生成token
            String token = Jwts.builder()
                    .setSubject(db_user.getUsername()) // 设置用户名作为 token 的主题
                    .claim("userId", db_user.getId()) // 添加自定义声明，例如用户 ID
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                    .signWith(SignatureAlgorithm.HS256, jwtSecret)
                    .compact();

//            用户名密码正确
            return new Result(new AuthenticationResult(token, db_user), Code.GET_OK, "success");
        } else {
//            用户名密码错误
            return new Result(null, Code.GET_ERR, "failure");
        }
    }

    //获取数据
    @GetMapping("/GetUserData/{id}")
    private Result GetUserData(@PathVariable Integer id) {

        User db_user = userLoginService.GetUserData(id);
        if (db_user != null) {
            System.out.println("YESSSSSSS");
            System.out.println(db_user);
//            用户名密码正确
            return new Result(db_user, Code.GET_OK, "success");
        } else {
//            用户名密码错误
            return new Result(null, Code.GET_ERR, "failure");
        }
    }
}

