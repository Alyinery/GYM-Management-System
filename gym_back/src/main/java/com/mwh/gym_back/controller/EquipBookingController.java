package com.mwh.gym_back.controller;


import com.mwh.gym_back.pojo.Equip;
import com.mwh.gym_back.pojo.EquipBookInfo;
import com.mwh.gym_back.service.EquipBookInfoService;
import com.mwh.gym_back.service.EquipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins ="*" ,maxAge = 3600)
@RestController
@RequestMapping("/gym/booking/")
public class EquipBookingController {
    @Autowired
    private EquipBookInfoService equipBookInfoService;

    @Autowired
    private EquipService equipService;

    //器材租借请求
    @PostMapping("/addEquipBookingInfo")
    public Result addEquipBookingInfo(@RequestBody EquipBookInfo equipBookInfo) {
        System.out.println("器材请求表\n"+equipBookInfo);
        int i = equipBookInfoService.addEquipBookInfo(equipBookInfo);
        //数量减一
        int i1 = equipService.decreaseEquipQuantityById(equipBookInfo.getEquipId());
        if(i != 0 && i1 != 0) {
            List<Equip> allEquipsData = equipService.getAllEquipsData();
            return new Result(allEquipsData,Code.SAVE_OK,"success");
        }else {
            return new Result(null,Code.SAVE_ERR,"failure");
        }
    }

    //归还器材
    @PostMapping("/returnEquipBookInfoById")
    public Result modifyEquipBookInfoById(@RequestParam("bookId") int equipBookInfoId,@RequestParam("equip_id") int equip_id) {
        System.out.println("equipBookInfoId"+equipBookInfoId);
        System.out.println("equip_id"+ equip_id);
        int i = equipBookInfoService.returnEquipBookInfo(equipBookInfoId);
        //数量加一
        System.out.println("equipIIIIId\n" + equip_id);
        int i1 = equipService.addEquipQuantityById(equip_id);
        if(i != 0 && i1 != 0) {
            return new Result(i,Code.UPDATE_OK,"success");
        }else  {
            return new Result(null,Code.UPDATE_ERR,"failure");
        }
    }

    //获取用户的器材租借数据
    @GetMapping("/getEquipBookingInfoById")
    public Result getEquipBookingInfoById(@RequestParam("user_id") int user_id) {
        List<Map<String, Object>> equipBookingInfoList = equipBookInfoService.getEquipBookingInfoByIdWithEquipName(user_id);
        System.out.println();
        if(!equipBookingInfoList.isEmpty()) {
            return new Result(equipBookingInfoList,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }


}
