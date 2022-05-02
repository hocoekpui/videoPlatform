package com.hezb.controller;

import com.hezb.model.CommonResponse;
import com.hezb.model.QueryUserResponse;
import com.hezb.model.RegisterUserRequest;
import com.hezb.model.UserLoginRequest;
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
    public CommonResponse<String> login(@RequestBody @Valid UserLoginRequest request) throws Exception {
        return CommonResponse.success(userService.login(request));
    }

    @GetMapping("/getUserInfo")
    public CommonResponse<QueryUserResponse> getUserInfo() {
        Long userId = userSupport.getCurrentUserId();
        return CommonResponse.success(userService.getUserInfo(userId));
    }
}
