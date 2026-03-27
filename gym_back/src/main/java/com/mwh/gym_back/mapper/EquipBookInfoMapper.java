package com.mwh.gym_back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mwh.gym_back.pojo.EquipBookInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface EquipBookInfoMapper extends BaseMapper<EquipBookInfo> {

    @Select("SELECT eb.*, e.e_name,u.u_name " +
            "            FROM equipbooking_info eb " +
            "            INNER JOIN equipment e ON eb.equipment_id = e.e_id " +
            "            INNER JOIN user_attribute u ON eb.user_id = u.u_id " +
            "            WHERE eb.user_id = #{userId}")
    List<Map<String, Object>> getEquipBookingInfoByIdWithEquipName(@Param("userId") int user_id);

    @Update("UPDATE equipbooking_info " +
            "SET state = 1 " +
            "WHERE id = #{equipBookInfoId}")
    int returnEquipBookById(@Param("equipBookInfoId") int equipBookInfoId);
}
