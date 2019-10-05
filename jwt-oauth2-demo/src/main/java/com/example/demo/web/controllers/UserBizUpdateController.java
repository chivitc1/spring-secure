package com.example.demo.web.controllers;

import com.example.demo.services.UserBizUpdateService;
import com.example.demo.web.model.EarnValueForm;
import com.example.demo.web.model.EarnValueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/biz/users")
public class UserBizUpdateController {
    @Autowired
    private UserBizUpdateService updateService;

    @PatchMapping("/ownerfield")
    public EarnValueResponse update(@RequestBody EarnValueForm earnValueForm) {
        Integer earnVal = updateService.updateRectrictFieldOwner(earnValueForm.getEarnValue());
        return new EarnValueResponse(earnVal);
    }
}
