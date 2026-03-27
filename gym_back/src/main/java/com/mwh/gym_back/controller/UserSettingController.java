package com.mwh.gym_back.controller;

import com.mwh.gym_back.pojo.UserAttribute;
import com.mwh.gym_back.service.UserAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins ="*" ,maxAge = 3600)
@RestController
@RequestMapping("/gym")
public class UserSettingController {
    @Autowired
    private UserAttributeService userAttributeService;

    @GetMapping("/getUserAttributeById/{id}")
    public Result getUserAttributeById(@PathVariable Integer id) {
        UserAttribute userAttribute = userAttributeService.getUserAttributeById(id);
        if(userAttribute != null) {
            return new Result(userAttribute,Code.GET_OK,"success");
        }else {
            return new Result(null,Code.GET_ERR,"failure");
        }
    }

    @PostMapping("/modifyUserAttributeById")
    public Result modifyUserAttributeById(@RequestBody UserAttribute userAttribute) {
        System.out.println("UserAttrivute\n" + userAttribute);
        int i = userAttributeService.modifyUserAttributeById(userAttribute);
        if(i != 0) {
            UserAttribute newUserAttribute = userAttributeService.getUserAttributeById(userAttribute.getId());
            return new Result(newUserAttribute,Code.UPDATE_OK,"success");
        }else {
            return new Result(null,Code.UPDATE_ERR,"failure");
        }

    }
}
