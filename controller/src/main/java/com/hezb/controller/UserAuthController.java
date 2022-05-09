package com.hezb.controller;

import com.hezb.pojo.CommonResponse;
import com.hezb.pojo.UserAuthResponse;
import com.hezb.service.UserAuthService;
import com.hezb.support.UserSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    private final UserSupport userSupport;
    private final UserAuthService userAuthService;

    public UserAuthController(UserSupport userSupport, UserAuthService userAuthService) {
        this.userSupport = userSupport;
        this.userAuthService = userAuthService;
    }

    @GetMapping("/getUserAuthorities")
    public CommonResponse<UserAuthResponse> getUserAuthorities() {
        Long userId = userSupport.getCurrentUserId();
        return CommonResponse.success(userAuthService.getUserAuthorities(userId));
    }
}
