package com.mwh.gym_back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mwh.gym_back.mapper.VenueMapper;
import com.mwh.gym_back.pojo.Venue;
import com.mwh.gym_back.service.VenuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenuesServiceImpl extends ServiceImpl<VenueMapper,Venue> implements VenuesService {
    @Autowired
    private VenueMapper venueMapper;

    public List<Venue> getVenueDataByProject(int projcet_id) {
        QueryWrapper<Venue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("v_project_id", projcet_id);
        return venueMapper.selectList(queryWrapper);
    }

    public List<Venue> getAllVeneueData() {
        return venueMapper.selectList(null);
    };

    public Venue getVenueDetailById(int v_id) {
        return venueMapper.selectById(v_id);
    }

    public int deleteVenueInfo(int VenueId) {
        return venueMapper.deleteById(VenueId);
    }
}
