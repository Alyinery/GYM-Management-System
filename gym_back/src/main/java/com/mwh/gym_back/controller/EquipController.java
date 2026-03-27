package com.mwh.gym_back.controller;

import com.mwh.gym_back.pojo.Equip;
import com.mwh.gym_back.pojo.Venue;
import com.mwh.gym_back.service.EquipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="*" ,maxAge = 3600)
@RestController
@RequestMapping("/gym")
public class EquipController {
    @Autowired
    private EquipService equipService;

    @GetMapping("/getEquips")
    public Result getEquipDataByProject(@RequestParam("project_id") int project_id) {
        List<Equip> EquipsList = equipService.getEquipDataByProject(project_id);
        System.out.println(EquipsList);
        if(!EquipsList.isEmpty()) {         //场馆列表不为空
            return new Result(EquipsList,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }

    @GetMapping("/getAllEquips")
    public Result getAllEquipsData() {
        List<Equip> EquipsAllList = equipService.getAllEquipsData();
        System.out.println(EquipsAllList);
        if(!EquipsAllList.isEmpty()) {         //场馆列表不为空
            return new Result(EquipsAllList,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }

    @PostMapping("/deleteEquipInfo")
    public Result deleteEquipInfo(@RequestParam("equipId") int equipId) {
        boolean b = equipService.removeById(equipId);
        if(b) {
            List<Equip> allEquipsData = equipService.getAllEquipsData();
            return new Result(allEquipsData,Code.DELETE_OK,"success");
        }else {
            return new Result(null,Code.DELETE_ERR,"failure");
        }

    }

    @PostMapping("/modigyEquipsInfo")
    public Result modigyEquipsInfo(@RequestBody List<Equip> equips) {
        System.out.println(equips);
        boolean b = equipService.updateBatchById(equips);
        if(b) {
            return new Result(null,Code.UPDATE_OK,"success");
        }else {
            return new Result(null,Code.UPDATE_ERR,"failure");
        }

    }
}
