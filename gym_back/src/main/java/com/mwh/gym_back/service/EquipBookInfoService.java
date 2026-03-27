package com.mwh.gym_back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mwh.gym_back.pojo.EquipBookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EquipBookInfoService extends IService<EquipBookInfo> {
    public int addEquipBookInfo(EquipBookInfo equipBookInfo);

    List<Map<String, Object>> getEquipBookingInfoByIdWithEquipName(int user_id);
    public int returnEquipBookInfo(int equipBookInfoId);
}
