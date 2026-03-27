package com.mwh.gym_back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mwh.gym_back.pojo.ActivityRegisterInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ActivityRegisterInfoMapper extends BaseMapper<ActivityRegisterInfo> {

    @Select("SELECT ar.*,ua.u_name,ua.u_phone,v.v_name " +
            "FROM activityregister_info ar " +
            "INNER JOIN user_attribute ua ON ua.u_id = ar.user_id " +
            "INNER JOIN venue v ON v.v_id = ar.venue_id " +
            "WHERE ar.activity_id = #{activityId} ")
    List<Map<String, Object>> getActivityRegisterInfoByActivityId(@Param("activityId") int activityId);


    @Select("SELECT ar.*,v.v_name,a.* " +
            "FROM activityregister_info ar " +
            "INNER JOIN venue v ON ar.venue_id = v.v_id " +
            "INNER JOIN activity a ON a.act_id = ar.activity_id " +
            "WHERE ar.user_id = #{userId}")
    List<Map<String,Object>> getActivityRegisterInfoByUserId(@Param("userId") int userId);
}
