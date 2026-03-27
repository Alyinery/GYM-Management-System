package com.mwh.gym_back.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;

@Data
@TableName("common_user")
public class User {
    @TableId("c_id")
    @TableField(value = "c_id")
    private int id;
    @TableField(value = "c_username")
    private String username;
    @TableField(value = "c_password")
    private String password;
    @TableField(value = "c_addtime")
    private Date addtime;
    @TableField(value = "c_isVip")
    private Integer isVip;
    @TableField(value = "c_email")
    private String email;
    @TableField(value = "c_phone")
    private String phone;
}
