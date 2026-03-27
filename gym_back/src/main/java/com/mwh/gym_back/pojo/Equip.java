package com.mwh.gym_back.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;

@Data
@TableName("equipment")
public class Equip {
    @TableField("e_id")
    @TableId("e_id")
    private int id;
    @TableField("e_project_id")
    private int projectId;
    @TableField("e_information")
    private String information;
    @TableField("e_pic")
    private String pic;
    @TableField("e_name")
    private String name;
    @TableField("e_addtime")
    private Date addtime;
    @TableField("e_price")
    private BigDecimal price;
    @TableField("e_quantity")
    private int quantity;
}
