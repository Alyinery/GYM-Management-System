package com.mwh.gym_back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mwh.gym_back.mapper.EquipBookInfoMapper;
import com.mwh.gym_back.mapper.EquipMapper;
import com.mwh.gym_back.pojo.EquipBookInfo;
import com.mwh.gym_back.pojo.VenueBookInfo;
import com.mwh.gym_back.service.EquipBookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EquipBookInfoServiceImpl extends ServiceImpl<EquipBookInfoMapper, EquipBookInfo> implements EquipBookInfoService {

    @Autowired
    private EquipBookInfoMapper equipBookInfoMapper;



    public int addEquipBookInfo(EquipBookInfo equipBookInfo) {
        int insert = equipBookInfoMapper.insert(equipBookInfo);

        return insert;
    }


    public List<Map<String, Object>> getEquipBookingInfoByIdWithEquipName(int user_id) {
        List<Map<String, Object>> equipBookingInfoList = equipBookInfoMapper.getEquipBookingInfoByIdWithEquipName(user_id);
        return equipBookingInfoList;
    }

//    归还器材
    public int returnEquipBookInfo(int  equipBookInfoId) {
        int update = equipBookInfoMapper.returnEquipBookById(equipBookInfoId);
        return update;
    }

}
