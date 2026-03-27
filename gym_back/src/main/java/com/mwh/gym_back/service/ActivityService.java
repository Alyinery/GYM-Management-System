package com.mwh.gym_back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mwh.gym_back.pojo.Activity;

import java.util.List;

public interface ActivityService extends IService<Activity> {

    List<Activity> getAllActivityInfo();
}
