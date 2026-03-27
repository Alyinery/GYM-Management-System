package com.mwh.gym_back.controller;

import com.mwh.gym_back.pojo.ActivityRegisterInfo;
import com.mwh.gym_back.service.ActivityRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins ="*" ,maxAge = 3600)
@RestController
@RequestMapping("/gym")
public class ActivityRegisterController {

    @Autowired
    private ActivityRegisterService activityRegisterService;

    @PostMapping("/addActivityRegisterInfo")
    public Result addActivityRegisterInfo(@RequestBody ActivityRegisterInfo activityRegisterInfo) {
        System.out.println("activityRegisterInfoId\n"+activityRegisterInfo.getVenueId());
        System.out.println("activityRegisterInfoIdUserId\n"+activityRegisterInfo.getUserId());
        System.out.println("activityRegisterInfoIdState\n"+activityRegisterInfo.getState());
        boolean save = activityRegisterService.save(activityRegisterInfo);
        if(save) {
            return new Result(null,Code.SAVE_OK,"success");
        }else {
            return new Result(null,Code.SAVE_ERR,"failure");
        }
    }

    @GetMapping("/getActivityRegisterInfoByActivityId")
    public Result getActivityRegisterInfoByActivityId(@RequestParam("activityId") int activityId) {

        List<Map<String,Object>> activityRegisterInfoList = activityRegisterService.getActivityRegisterInfoByActivityId(activityId);
        if(!activityRegisterInfoList.isEmpty()) {
            return new Result(activityRegisterInfoList,Code.GET_OK,"success");
        }else{
            return new Result(null,Code.GET_ERR,"failure");
        }
    }

    @GetMapping("/getActivityRegisterInfoByUserId")
    public Result getActivityRegisterInfoByUserId(@RequestParam("userId") int userId) {
        List<Map<String, Object>> activityRegisterInfoList = activityRegisterService.getActivityRegisterInfoByUserId(userId);
        if(!activityRegisterInfoList.isEmpty()) {
            return new Result(activityRegisterInfoList,Code.GET_OK,"success");
        }else {
            return new Result(null, Code.GET_ERR,"failure");
        }
    }
}
