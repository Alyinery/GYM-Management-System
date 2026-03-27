package com.mwh.gym_back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mwh.gym_back.pojo.ActivityRegisterInfo;

import java.util.List;
import java.util.Map;

public interface ActivityRegisterService extends IService<ActivityRegisterInfo> {
    List<Map<String,Object>> getActivityRegisterInfoByActivityId(int activityId);

    List<Map<String,Object>> getActivityRegisterInfoByUserId(int userId);
}
