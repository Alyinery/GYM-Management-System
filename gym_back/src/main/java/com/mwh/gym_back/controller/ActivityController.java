package com.mwh.gym_back.controller;

import com.mwh.gym_back.pojo.Activity;
import com.mwh.gym_back.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="*" ,maxAge = 3600)
@RestController
@RequestMapping("/gym")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/getAllActivityInfo")
    public Result getAllActivityInfo() {
        List<Activity> allActivityInfo = activityService.getAllActivityInfo();
        if(!allActivityInfo.isEmpty()) {
            return new Result(allActivityInfo,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }

    @PostMapping("/deleteActivityInfo")
    public Result deleteActivityInfo(@RequestParam("activityId") int activityId) {
        System.out.println("activityId\n"+activityId);
        boolean b = activityService.removeById(activityId);
        if(b) {
            List<Activity> allActivityInfo = activityService.getAllActivityInfo();
            return new Result(allActivityInfo,Code.DELETE_OK,"success");
        }else {
            return new Result(null,Code.DELETE_ERR,"success");
        }
    }

    @PostMapping("/addActivityInfo")
    public Result addActivityInfo(@RequestBody Activity activity) {
        System.out.println("NewActivity\n"+activity);
        boolean save = activityService.save(activity);
        if(save) {
            List<Activity> allActivityInfo = activityService.getAllActivityInfo();
            return new Result(allActivityInfo,Code.SAVE_OK,"success");
        }else{
            return new Result(null, Code.SAVE_ERR,"failure");
        }

    }

}
