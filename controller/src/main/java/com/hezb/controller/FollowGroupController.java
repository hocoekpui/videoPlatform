package com.hezb.controller;

import com.hezb.domain.FollowGroup;
import com.hezb.pojo.AddFollowGroupRequest;
import com.hezb.pojo.CommonResponse;
import com.hezb.service.FollowGroupService;
import com.hezb.support.UserSupport;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/followGroup")
public class FollowGroupController {

    private final UserSupport userSupport;
    private final FollowGroupService followGroupService;

    public FollowGroupController(UserSupport userSupport, FollowGroupService followGroupService) {
        this.userSupport = userSupport;
        this.followGroupService = followGroupService;
    }

    @PostMapping("/addGroup")
    public CommonResponse<Integer> addGroup(@RequestBody @Valid AddFollowGroupRequest request) {
        Long userId = userSupport.getCurrentUserId();
        return CommonResponse.success(followGroupService.addGroup(userId, request));
    }

    @GetMapping("/getGroup")
    public CommonResponse<List<FollowGroup>> getGroup() {
        Long userId = userSupport.getCurrentUserId();
        return CommonResponse.success(followGroupService.getGroup(userId));
    }
}
