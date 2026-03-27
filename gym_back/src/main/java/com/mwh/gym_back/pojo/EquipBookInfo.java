package com.mwh.gym_back.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("equipbooking_info")
public class EquipBookInfo {
    @TableId("id")
    private int id;
    @TableField("user_id")
    private int userId;
    @TableField("equipment_id")
    private int equipId;
    @TableField("rentStartTime")
    private Date rentStartTime;
    @TableField("rentEndTime")
    private Date rentEndTime;
    @TableField("state")
    private String state;       // 0-待确认，1-已借出，2-已归还
}
