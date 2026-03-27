package com.mwh.gym_back;

import com.mwh.gym_back.mapper.UserMapper;
import com.mwh.gym_back.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Newtest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void getAll(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
