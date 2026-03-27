package com.mwh.gym_back.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("venuebooking_info")
public class VenueBookInfo {
    @TableId("id")
    @TableField("id")
    private int id;
    @TableField("venue_id")
    private int venueId;
    @TableField("user_id")
    private int userId;
    @TableField("bookStartTime")
    private Date bookStartTime;
    @TableField("bookEndTime")
    private Date bookEndTime;
    @TableField("addtime")
    private Date addtime;   //创建时间
    @TableField("information")
    private String information;
    @TableField("updatetime")
    private Date updatetime;
    @TableField("state")
    private String state;   // 0 - 待确认，1 - 已确认，2 - 已取消
}
