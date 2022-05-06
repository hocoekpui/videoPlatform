package com.hezb.controller;

import com.hezb.annotation.ApiLimitedRole;
import com.hezb.constant.RoleCodeConstant;
import com.hezb.domain.UserMoment;
import com.hezb.pojo.CommonResponse;
import com.hezb.pojo.UserMomentReleaseRequest;
import com.hezb.service.UserMomentService;
import com.hezb.support.UserSupport;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/moment")
public class UserMomentController {

    private final UserSupport userSupport;
    private final UserMomentService userMomentService;

    public UserMomentController(UserSupport userSupport, UserMomentService userMomentService) {
        this.userSupport = userSupport;
        this.userMomentService = userMomentService;
    }

    @ApiLimitedRole(roleCodeList = {RoleCodeConstant.Lv0})
    @PostMapping("/releaseMoment")
    public CommonResponse<Long> releaseMoment(@RequestBody @Valid UserMomentReleaseRequest request) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        Long userId = userSupport.getCurrentUserId();
        return CommonResponse.success(userMomentService.releaseMoment(userId, request));
    }

    @GetMapping("/getSubscribedMoment")
    public CommonResponse<List<UserMoment>> getSubscribedMoment() {
        Long userId = userSupport.getCurrentUserId();
        return CommonResponse.success(userMomentService.getSubscribedMoment(userId));
    }

}
