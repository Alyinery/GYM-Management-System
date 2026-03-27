package com.mwh.gym_back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mwh.gym_back.pojo.Equip;
import com.mwh.gym_back.pojo.Venue;

import java.util.List;

public interface EquipService extends IService<Equip> {
    public List<Equip> getEquipDataByProject(int project_id);

    public List<Equip> getAllEquipsData();

    public int decreaseEquipQuantityById(int equip_id);

    public int addEquipQuantityById(int equip_id);
}
