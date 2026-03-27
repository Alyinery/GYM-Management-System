package com.mwh.gym_back.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;

@Data
@TableName("admin_user")
public class AdminUser {
    @TableId("a_id")
    @TableField(value = "a_id")
    private int id;
    @TableField(value = "a_username")
    private String username;
    @TableField(value = "a_password")
    private String password;
    @TableField(value = "a_addtime")
    private Date addtime;
    @TableField(value = "a_phone")
    private String phone;
}
