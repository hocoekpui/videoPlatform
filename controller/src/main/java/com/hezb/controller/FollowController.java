package com.hezb.controller;

import com.hezb.pojo.*;
import com.hezb.service.UserFollowService;
import com.hezb.support.UserSupport;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final UserSupport userSupport;
    private final UserFollowService userFollowService;

    public FollowController(UserSupport userSupport, UserFollowService userFollowService) {
        this.userSupport = userSupport;
        this.userFollowService = userFollowService;
    }

    @PostMapping("/followUser")
    public CommonResponse<Integer> followUser(@RequestBody @Valid FollowUserRequest request) {
        Long userId = userSupport.getCurrentUserId();
        return CommonResponse.success(userFollowService.followUser(userId, request));
    }

    @PostMapping("/unfollowUser")
    public CommonResponse<Integer> unfollowUser(@RequestBody @Valid UnFollowUserRequest request) {
        Long userId = userSupport.getCurrentUserId();
        return CommonResponse.success(userFollowService.unFollowUser(userId, request));
    }

    @GetMapping("/getFollowUsers")
    public CommonResponse<List<FollowUserInfoResponse>> getFollowUsers() {
        Long userId = userSupport.getCurrentUserId();
        return CommonResponse.success(userFollowService.getFollowingUsers(userId));
    }

    @GetMapping("/getFanUsers")
    public CommonResponse<List<FanUserInfoResponse>> getFanUsers() {
        Long userId = userSupport.getCurrentUserId();
        return CommonResponse.success(userFollowService.getFanUsers(userId));
    }
}
