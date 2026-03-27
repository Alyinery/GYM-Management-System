package com.mwh.gym_back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mwh.gym_back.mapper.ActivityRegisterInfoMapper;
import com.mwh.gym_back.pojo.ActivityRegisterInfo;
import com.mwh.gym_back.service.ActivityRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ActivityRegisterServiceImpl extends ServiceImpl<ActivityRegisterInfoMapper, ActivityRegisterInfo> implements ActivityRegisterService {

    @Autowired ActivityRegisterInfoMapper activityRegisterInfoMapper;

    public List<Map<String, Object>> getActivityRegisterInfoByActivityId(int activityId) {
        List<Map<String, Object>> activityRegisterInfoList = activityRegisterInfoMapper.getActivityRegisterInfoByActivityId(activityId);
        return activityRegisterInfoList;
    }

    public List<Map<String,Object>> getActivityRegisterInfoByUserId(int userId) {
        List<Map<String, Object>> activityRegisterInfoList = activityRegisterInfoMapper.getActivityRegisterInfoByUserId(userId);
        return activityRegisterInfoList;
    }

}
