package com.mwh.gym_back.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;

@Data
@TableName("venue")
public class Venue {
    @TableId("v_id")
    @TableField("v_id")
    private int id;
    @TableField("v_name")
    private String name;
    @TableField("v_project_id")
    private int projectId;
    @TableField("v_information")
    private String information;
    @TableField("v_pic")
    private String pic;
    @TableField("v_addtime")
    private Date addtime;
    @TableField("v_stars")
    private int stars;
    @TableField("v_price")
    private BigDecimal price;
    @TableField("v_state")
    private int state;  //1-开放,0-封闭
}
