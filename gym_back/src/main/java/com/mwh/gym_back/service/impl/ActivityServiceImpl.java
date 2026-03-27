package com.mwh.gym_back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mwh.gym_back.mapper.ActivityMapper;
import com.mwh.gym_back.pojo.Activity;
import com.mwh.gym_back.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    public List<Activity> getAllActivityInfo(){
        List<Activity> activities = activityMapper.selectList(null);
        return activities;
    }
}
