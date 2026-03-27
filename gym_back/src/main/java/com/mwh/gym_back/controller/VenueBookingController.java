package com.mwh.gym_back.controller;


import com.mwh.gym_back.pojo.EquipBookInfo;
import com.mwh.gym_back.pojo.VenueBookInfo;
import com.mwh.gym_back.service.VenueBookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins ="*" ,maxAge = 3600)
@RestController
@RequestMapping("/gym/booking/")
public class VenueBookingController {
    @Autowired
    private VenueBookInfoService venueBookInfoService;


    //根据用户ID获取刚预约的数据
    @GetMapping("/getBookingListByUserId")
    public Result getBookingInfoByUserId(@RequestParam("user_id") int user_id) {
        List<Map<String, Object>> bookingInfoList = venueBookInfoService.getVenueBookingInfoByIdWithVenueName(user_id);
        System.out.println(bookingInfoList);
        if(!bookingInfoList.isEmpty()) {
            return new Result(bookingInfoList,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }

    //根据用户ID获取预约过的数据
    @GetMapping("/getBookedListByUserId")
    public Result getBookedInfoByUserId(@RequestParam("user_id") int user_id) {
        List<Map<String, Object>> bookedInfoList = venueBookInfoService.getVenueBookedInfoByIdWithVenueName(user_id);
        System.out.println(bookedInfoList);
        if(!bookedInfoList.isEmpty()) {
            return new Result(bookedInfoList,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }

    //根据用户ID获取预约过期的数据
    @GetMapping("/getUnBookedListByUserId")
    public Result getUnBookedInfoByUserId(@RequestParam("user_id") int user_id) {
        List<Map<String, Object>> unbookedInfoList = venueBookInfoService.getVenueUnBookedInfoByIdWithVenueName(user_id);
        System.out.println(unbookedInfoList);
        if(!unbookedInfoList.isEmpty()) {
            return new Result(unbookedInfoList,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }



    //获取所有新预约信息
    @GetMapping("/getAllBookingInfo")
    public Result getAllBookingInfo() {
        List<Map<String, Object>> allBookingInfo = venueBookInfoService.getAllBookingInfo();
        if (!allBookingInfo.isEmpty()) {
            return new Result(allBookingInfo,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }

    //获取所有已处理的预约信息
    @GetMapping("/getAllBookedInfo")
    public Result getAllBookedInfo() {
        List<Map<String, Object>> allBookedInfo = venueBookInfoService.getAllBookedInfo();
        if (!allBookedInfo.isEmpty()) {
            return new Result(allBookedInfo,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }


    @GetMapping("/getAllUnBookedInfo")
    public Result getAllUnBookedInfo() {
        List<Map<String, Object>> allUnBookedInfo = venueBookInfoService.getAllUnBookedInfo();
        if (!allUnBookedInfo.isEmpty()) {
            return new Result(allUnBookedInfo,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }

    //更新预约信息,未完成 ==> 已完成
    @PostMapping("/modifyBookInfo")
    public Result modifyBookInfo(@RequestBody VenueBookInfo venueBookInfo) {
        System.out.println("modify"+ venueBookInfo);
        int i = venueBookInfoService.modifyBookInfo(venueBookInfo);
        if(i != 0) {
            return new Result(i,Code.UPDATE_OK,"success");
        }else {
            return new Result(null,Code.UPDATE_ERR,"failure");
        }
    }

    //取消预约
    @PostMapping("/cancelBookingInfo")
    public Result cancelBookingInfo(@RequestBody VenueBookInfo venueBookInfo) {
        System.out.println("cancel\n"+venueBookInfo);
        int i = venueBookInfoService.cancelBookingInfo(venueBookInfo);
        if(i != 0) {
            List<Map<String, Object>> venueBookingInfoByIdWithVenueName = venueBookInfoService.getVenueBookingInfoByIdWithVenueName(venueBookInfo.getUserId());
            return new Result(venueBookingInfoByIdWithVenueName, Code.UPDATE_ERR, "failure");
        }else {
            return new Result(null, Code.UPDATE_ERR, "failure");
        }
    }


    //添加预约信息
    @PostMapping("/addBookingInfo")
    public Result addBookingInfo(@RequestBody VenueBookInfo venueBookInfo) {
        System.out.println("11111"+ venueBookInfo);
        int i = venueBookInfoService.addBookingInfo(venueBookInfo);
        System.out.println("IIIIIIIII"+i);
        if(i != 0){
            return new Result(i,Code.SAVE_OK,"success");
        }else {
            return new Result(null,Code.SAVE_ERR,"failure");
        }
    }


}
