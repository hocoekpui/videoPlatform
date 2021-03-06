package com.hezb.controller;

import com.hezb.pojo.*;
import com.hezb.service.UserService;
import com.hezb.support.UserSupport;
import com.hezb.util.RSAUtil;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserSupport userSupport;

    public UserController(UserService userService, UserSupport userSupport) {
        this.userService = userService;
        this.userSupport = userSupport;
    }

    @GetMapping("/rsa-pks")
    public CommonResponse<String> getRSAPublicKey() {
        return CommonResponse.success(RSAUtil.getPublicKeyStr());
    }

    @PostMapping("/register")
    public CommonResponse<Long> register(@RequestBody @Valid RegisterUserRequest request) throws Exception {
        return CommonResponse.success(userService.register(request));
    }

    @PostMapping("/login")
    public CommonResponse<UserLoginResponse> login(@RequestBody @Valid UserLoginRequest request) throws Exception {
        return CommonResponse.success(userService.login(request));
    }

    @PostMapping("/logout")
    public CommonResponse<Integer> logout(@RequestHeader String refreshToken) {
        Long userId = userSupport.getCurrentUserId();
        return CommonResponse.success(userService.logout(userId, refreshToken));
    }

    @PostMapping("/refreshToken")
    public CommonResponse<String> refreshToken(@RequestHeader String refreshToken) throws Exception {
        return CommonResponse.success(userService.refreshToken(refreshToken));
    }

    @GetMapping("/getUserInfo")
    public CommonResponse<QueryUserResponse> getUserInfo() {
        Long userId = userSupport.getCurrentUserId();
        return CommonResponse.success(userService.getUserInfo(userId));
    }

    @PostMapping("/updateUserInfo")
    public CommonResponse<Integer> updateUserInfo(@RequestBody @Valid UserInfoUpdateRequest request) {
        Long userId = userSupport.getCurrentUserId();
        return CommonResponse.success(userService.updateUserInfo(userId, request));
    }

    @GetMapping("/getUserInfoList")
    public CommonResponse<PageResult<UserQueryResponse>> getUserInfoList(@Valid UserQueryRequest request) {
        Long userId = userSupport.getCurrentUserId();
        return CommonResponse.success(userService.getUserInfoList(userId, request));
    }
}
