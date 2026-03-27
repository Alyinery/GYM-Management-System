package com.mwh.gym_back.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mwh.gym_back.pojo.Venue;
import com.mwh.gym_back.service.VenuesService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="*" ,maxAge = 3600)
@RestController
@RequestMapping("/gym")
public class VenuesController {
    @Autowired
    private VenuesService venuesService;

    @GetMapping("/getVenueDetail/{v_id}")
    public Result getVenueDetailById(@PathVariable int v_id) {
        Venue venueDetail = venuesService.getVenueDetailById(v_id);
        System.out.println("VVVVVVV");
        System.out.println(venueDetail);
        if(venueDetail != null) {
            return new Result(venueDetail,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }


    @GetMapping("/getVenues")
    public Result getVenueDataByProject(@RequestParam("project_id") int project_id) {
        List<Venue> VenuesList = venuesService.getVenueDataByProject(project_id);
        System.out.println(VenuesList);
        if(!VenuesList.isEmpty()) {         //场馆列表不为空
            return new Result(VenuesList,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }

    @GetMapping("/getAllVenues")
    public Result getAllVeneueData() {
        List<Venue> VenuesAllList = venuesService.getAllVeneueData();
        System.out.println(VenuesAllList);
        if(!VenuesAllList.isEmpty()) {         //场馆列表不为空
            return new Result(VenuesAllList,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }

    @PostMapping("/modifyVenueById")
    public Result modifyVenueById(@RequestBody Venue venue) {
        System.out.println("modifyVenue\n"+venue);
        boolean b = venuesService.updateById(venue);
        if(b) {
            Venue NewVenueDetail = venuesService.getVenueDetailById(venue.getId());
            return new Result(NewVenueDetail,Code.UPDATE_OK,"success");
        }else {
            return new Result(null,Code.UPDATE_ERR,"failure");
        }
    }

    @PostMapping("/addNewVenueInfo")
    public Result addNewVenueInfo(@RequestBody Venue venue) {
        System.out.println("NNNNNEEEWWVenue\n"+venue);
        boolean save = venuesService.save(venue);
        if(save){
            List<Venue> allVeneueData = venuesService.getAllVeneueData();
            return new Result(allVeneueData,Code.SAVE_OK,"success");
        }else {
            return new Result(null,Code.SAVE_ERR,"failure");
        }
    }

    @PostMapping("/deleteVenueInfo")
    public Result deleteVenueInfo(@RequestParam("VenueId") int VenueId) {
        System.out.println("VVVenueIId\n"+VenueId);
        int i = venuesService.deleteVenueInfo(VenueId);
        if(i != 0) {
            List<Venue> allVeneueData = venuesService.getAllVeneueData();
            return new Result(allVeneueData,Code.DELETE_OK,"success");
        }else {
            return new Result(null,Code.DELETE_ERR,"failure");
        }
    }
}
