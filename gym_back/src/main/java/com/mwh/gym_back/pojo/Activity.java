package com.mwh.gym_back.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("activity")
public class Activity {
    @TableId("act_id")
    private int id;
    @TableField("act_name")
    private String name;
    @TableField("act_information")
    private String information;
    @TableField("act_startTime")
    private Date startTime;
    @TableField("act_endTime")
    private Date endTime;
    @TableField("act_venueId")
    private int venueId;
    @TableField("act_pic")
    private String pic;
}
