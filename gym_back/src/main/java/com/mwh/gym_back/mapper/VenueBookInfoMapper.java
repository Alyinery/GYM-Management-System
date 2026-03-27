package com.mwh.gym_back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mwh.gym_back.pojo.VenueBookInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface VenueBookInfoMapper extends BaseMapper<VenueBookInfo> {
    @Select("SELECT vb.*, v.v_name,u.u_name,v.v_price " +
            "FROM venueBooking_info vb " +
            "INNER JOIN venue v ON vb.venue_id = v.v_id " +
            "INNER JOIN user_attribute u ON vb.user_id = u.u_id " +
            "WHERE vb.user_id = #{userId} AND vb.state = '0'")
    List<Map<String, Object>> getVenueBookingInfoByIdWithVenueName(@Param("userId") int user_id);

    @Select("SELECT vb.*, v.v_name,u.u_name,v.v_price " +
            "FROM venueBooking_info vb " +
            "INNER JOIN venue v ON vb.venue_id = v.v_id " +
            "INNER JOIN user_attribute u ON vb.user_id = u.u_id " +
            "WHERE vb.user_id = #{userId} AND vb.state = '1'")
    List<Map<String, Object>> getVenueBookedInfoByIdWithVenueName(@Param("userId") int user_id);

    @Select("SELECT vb.*, v.v_name,u.u_name,v.v_price " +
            "FROM venueBooking_info vb " +
            "INNER JOIN venue v ON vb.venue_id = v.v_id " +
            "INNER JOIN user_attribute u ON vb.user_id = u.u_id " +
            "WHERE vb.user_id = #{userId} AND vb.state = '2'")
    List<Map<String, Object>> getVenueUnBookedInfoByIdWithVenueName(@Param("userId") int user_id);

    @Select("SELECT vb.*, v.v_name,u.u_name,v.v_price " +
            "FROM venueBooking_info vb " +
            "INNER JOIN venue v ON vb.venue_id = v.v_id " +
            "INNER JOIN user_attribute u ON vb.user_id = u.u_id " +
            "WHERE vb.state = '0'")
    List<Map<String, Object>> getAllBookingInfo();

    @Select("SELECT vb.*, v.v_name,u.u_name,v.v_price " +
            "FROM venueBooking_info vb " +
            "INNER JOIN venue v ON vb.venue_id = v.v_id " +
            "INNER JOIN user_attribute u ON vb.user_id = u.u_id " +
            "WHERE vb.state = '1'")
    List<Map<String, Object>> getAllBookedInfo();

    @Select("SELECT vb.*, v.v_name,u.u_name,v.v_price " +
            "FROM venueBooking_info vb " +
            "INNER JOIN venue v ON vb.venue_id = v.v_id " +
            "INNER JOIN user_attribute u ON vb.user_id = u.u_id " +
            "WHERE vb.state = '2'")
    List<Map<String, Object>> getAllUnBookedInfo();
}

