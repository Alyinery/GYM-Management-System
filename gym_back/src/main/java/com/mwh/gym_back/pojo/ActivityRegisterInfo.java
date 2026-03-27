package com.mwh.gym_back.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("activityregister_info")
public class ActivityRegisterInfo {
    @TableId("id")
    private int id;
    @TableField("user_id")
    private int userId;
    @TableField("venue_id")
    private int venueId;
    @TableField("state")
    private String state;       // 0-待审核，1-已通过，2-已拒绝
    @TableField("activity_id")
    private int activityId;
}
