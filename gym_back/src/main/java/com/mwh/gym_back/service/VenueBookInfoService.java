package com.mwh.gym_back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mwh.gym_back.pojo.VenueBookInfo;

import java.util.List;
import java.util.Map;

public interface VenueBookInfoService extends IService<VenueBookInfo> {

    public List<Map<String, Object>> getVenueBookingInfoByIdWithVenueName(int user_id);
    public List<Map<String, Object>> getVenueBookedInfoByIdWithVenueName(int user_id);
    public List<Map<String, Object>> getVenueUnBookedInfoByIdWithVenueName(int user_id);

    public int addBookingInfo(VenueBookInfo venueBookInfo);

    public int modifyBookInfo(VenueBookInfo venueBookInfo);

    public int cancelBookingInfo(VenueBookInfo venueBookInfo);

    public List<Map<String, Object>> getAllBookingInfo();


    public List<Map<String, Object>> getAllBookedInfo();
    public List<Map<String, Object>> getAllUnBookedInfo();
}
