package com.mwh.gym_back.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_attribute")
public class UserAttribute {
    @TableId("u_id")
    private int id;
    @TableField("u_name")
    private String name;
    @TableField("u_gender")
    private String gender;
    @TableField("u_height")
    private Double height;
    @TableField("u_weight")
    private Double weight;
    @TableField("u_birthday")
    private Date birthday;
    @TableField("u_isVip")
    private Integer isVip;
    @TableField("u_phone")
    private  String phone;
    @TableField("u_avator")
    private String avator;
    @TableField("u_email")
    private String email;
}
