package com.mwh.gym_back.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mwh.gym_back.pojo.Venue;

import java.util.List;

public interface VenuesService extends IService<Venue> {
    public List<Venue> getVenueDataByProject(int projcet_id);

    public Venue getVenueDetailById(int v_id);
    public List<Venue> getAllVeneueData();

    public int deleteVenueInfo(int VenueId);
}
