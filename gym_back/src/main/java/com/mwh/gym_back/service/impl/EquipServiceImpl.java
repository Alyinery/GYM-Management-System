package com.mwh.gym_back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mwh.gym_back.mapper.EquipMapper;
import com.mwh.gym_back.mapper.VenueMapper;
import com.mwh.gym_back.pojo.Equip;
import com.mwh.gym_back.pojo.Venue;
import com.mwh.gym_back.service.EquipService;
import com.mwh.gym_back.service.VenuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipServiceImpl extends ServiceImpl<EquipMapper, Equip> implements EquipService {
    @Autowired
    private EquipMapper equipMapper;


    public List<Equip> getEquipDataByProject(int projcet_id) {
        QueryWrapper<Equip> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("e_project_id", projcet_id);
        return equipMapper.selectList(queryWrapper);
    }

    public List<Equip> getAllEquipsData() {
        return equipMapper.selectList(null);
    };

    //器材租借,数量减一
    public int decreaseEquipQuantityById(int equip_id) {
        int i = equipMapper.decreaseEquipQuantityById(equip_id);
        return i;
    }

    public int addEquipQuantityById(int equip_id) {
        int i = equipMapper.addEquipQuantityById(equip_id);
        return i;
    }
}
