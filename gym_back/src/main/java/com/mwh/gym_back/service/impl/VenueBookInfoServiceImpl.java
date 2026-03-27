package com.mwh.gym_back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mwh.gym_back.mapper.VenueBookInfoMapper;
import com.mwh.gym_back.pojo.VenueBookInfo;
import com.mwh.gym_back.service.VenueBookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VenueBookInfoServiceImpl extends ServiceImpl<VenueBookInfoMapper, VenueBookInfo> implements VenueBookInfoService {

    @Autowired
    private VenueBookInfoMapper venueBookInfoMapper;

    //根据用户ID获取待审核的预约订单
    public List<Map<String, Object>> getVenueBookingInfoByIdWithVenueName(int user_id) {
        List<Map<String, Object>> bookingInfoList = venueBookInfoMapper.getVenueBookingInfoByIdWithVenueName(user_id);
        return bookingInfoList;
    }

    ////根据用户ID获取已审核的预约订单
    public List<Map<String, Object>> getVenueBookedInfoByIdWithVenueName(int user_id) {
        List<Map<String, Object>> bookedInfoList = venueBookInfoMapper.getVenueBookedInfoByIdWithVenueName(user_id);
        return bookedInfoList;
    }

    ////根据用户ID获取已失效的预约订单
    public List<Map<String, Object>> getVenueUnBookedInfoByIdWithVenueName(int user_id) {
        List<Map<String, Object>> unbookedInfoList = venueBookInfoMapper.getVenueUnBookedInfoByIdWithVenueName(user_id);
        return unbookedInfoList;
    }

    //添加预约订单
    public int addBookingInfo(VenueBookInfo venueBookInfo) {
        int insert = venueBookInfoMapper.insert(venueBookInfo);
        return insert;
    }

    public int modifyBookInfo(VenueBookInfo venueBookInfo) {
        int update = venueBookInfoMapper.updateById(venueBookInfo);
        return update;
    }

    public int cancelBookingInfo(VenueBookInfo venueBookInfo) {
        int cancel = venueBookInfoMapper.updateById(venueBookInfo);
        return cancel;
    }



    //获取所有待审核的预约订单，管理员查看
    public List<Map<String, Object>> getAllBookingInfo() {
        List<Map<String, Object>> allBookingInfo = venueBookInfoMapper.getAllBookingInfo();
        return allBookingInfo;
    }

    public List<Map<String, Object>> getAllBookedInfo(){
        List<Map<String, Object>> allBookedInfo = venueBookInfoMapper.getAllBookedInfo();
        return allBookedInfo;
    }

    public List<Map<String, Object>> getAllUnBookedInfo(){
        List<Map<String, Object>> allUnBookedInfo = venueBookInfoMapper.getAllUnBookedInfo();
        return allUnBookedInfo;
    }
}
