package com.example.demo.web.controllers;

import com.example.demo.services.UserBizService;
import com.example.demo.web.model.EarnValueForm;
import com.example.demo.web.model.EarnValueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/biz/users")
public class UserBizUpdateController {
    @Autowired
    private UserBizService updateService;

    @PatchMapping("/{userId}/ownerfield")
    public EarnValueResponse updateOwnerfield(@PathVariable("userId") Integer userId, @RequestBody EarnValueForm earnValueForm) {
        Integer earnVal = updateService.updateRectrictFieldOwner(userId, earnValueForm.getEarnValue());
        return new EarnValueResponse(earnVal);
    }

    @PatchMapping("/{userId}/sysadminfield")
    public EarnValueResponse updateSysadminfield(@PathVariable("userId") Integer userId, @RequestBody EarnValueForm earnValueForm) {
        Integer earnVal = updateService.updateRectrictFieldSysadm(userId, earnValueForm.getEarnValue());
        return new EarnValueResponse(earnVal);
    }

    @PatchMapping("/{userId}/teamadminfield")
    public EarnValueResponse updateTeamadminfield(@PathVariable("userId") Integer userId, @RequestBody EarnValueForm earnValueForm) {
        Integer earnVal = updateService.updateRectrictFieldTeamAdmin(userId, earnValueForm.getEarnValue());
        return new EarnValueResponse(earnVal);
    }

    @PatchMapping("/{userId}/teamleaderfield")
    public EarnValueResponse updateTeamleaderfield(@PathVariable("userId") Integer userId, @RequestBody EarnValueForm earnValueForm) {
        Integer earnVal = updateService.updateRectrictFieldTeamLeader(userId, earnValueForm.getEarnValue());
        return new EarnValueResponse(earnVal);
    }

    @PatchMapping("/{userId}/metorfield")
    public EarnValueResponse updateMentorfield(@PathVariable("userId") Integer userId, @RequestBody EarnValueForm earnValueForm) {
        Integer earnVal = updateService.updateRectrictFieldMentor(userId, earnValueForm.getEarnValue());
        return new EarnValueResponse(earnVal);
    }
}
