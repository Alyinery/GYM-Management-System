package com.mwh.gym_back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mwh.gym_back.pojo.Equip;
import com.mwh.gym_back.pojo.Venue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EquipMapper extends BaseMapper<Equip> {

    //器材租借，数量减一
    @Update("UPDATE equipment SET e_quantity = e_quantity - 1 WHERE e_id = #{equip_id}")
    int decreaseEquipQuantityById(@Param("equip_id") int equip_id);

    //器材归还，数量加一
    @Update("UPDATE equipment SET e_quantity = e_quantity + 1 WHERE e_id = #{equip_id}")
    int addEquipQuantityById(@Param("equip_id") int equip_id);
}
