package com.hezb.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hezb.domain.FollowUser;
import com.hezb.exception.enums.FollowExceptionEnum;
import com.hezb.exception.enums.UserExceptionEnum;
import com.hezb.mapper.FollowUserMapper;
import com.hezb.mapper.UserMapper;
import com.hezb.pojo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserFollowService {

    private final FollowUserMapper followUserMapper;
    private final UserMapper userMapper;

    public UserFollowService(FollowUserMapper followUserMapper, UserMapper userMapper) {
        this.followUserMapper = followUserMapper;
        this.userMapper = userMapper;
    }

    @Transactional
    public Integer followUser(Long userId, FollowUserRequest request) {
        /*检测是否关注自己*/
        FollowExceptionEnum.FOLLOW_YOURSELF.assertTrue(userId.compareTo(request.getFollowUserId()) != 0);
        /*检查用户编号合法性*/
        UserExceptionEnum.USER_NOT_EXIST.assertTrue(userMapper.selectById(userId) != null);
        UserExceptionEnum.USER_NOT_EXIST.assertTrue(userMapper.selectById(request.getFollowUserId()) != null);
        /*删除存量关注记录*/
        followUserMapper.delete(Wrappers.<FollowUser>lambdaQuery().eq(FollowUser::getUserId, userId).eq(FollowUser::getFollowUserId, request.getFollowUserId()));
        /*新增关注记录*/
        return followUserMapper.insert(FollowUser.builder().userId(userId).followUserId(request.getFollowUserId()).groupId(request.getGroupId()).createTime(new Date()).build());
    }

    public Integer unFollowUser(Long userId, UnFollowUserRequest request) {
        /*删除存量关注记录*/
        return followUserMapper.delete(Wrappers.<FollowUser>lambdaQuery().eq(FollowUser::getUserId, userId).eq(FollowUser::getFollowUserId, request.getFollowUserId()));
    }

    public List<FollowUserInfoResponse> getFollowingUsers(Long userId) {
        /*关注用户信息列表*/
        List<FollowUserInfoResponse> responses = new ArrayList<>();
        /*查询当前用户的关注用户信息*/
        List<FollowUserInfo> followUserInfoList = followUserMapper.getFollowUserInfo(userId);
        /*根据分组名称分组*/
        Map<String, List<FollowUserInfo>> followUserInfoMap = followUserInfoList.stream().collect(Collectors.groupingBy(FollowUserInfo::getGroupName, LinkedHashMap::new, Collectors.toList()));
        followUserInfoMap.forEach((groupName, userInfoList) -> {
            FollowUserInfoResponse followUserInfoResponse = FollowUserInfoResponse.builder().groupName(groupName).userInfoSummary(new ArrayList<>()).build();
            userInfoList.stream().forEach(userInfo -> {
                FollowUserInfoResponse.UserInfoSummary userInfoSummary = FollowUserInfoResponse.UserInfoSummary.builder().build();
                BeanUtils.copyProperties(userInfo, userInfoSummary);
                followUserInfoResponse.getUserInfoSummary().add(userInfoSummary);
            });
            responses.add(followUserInfoResponse);
        });
        return responses;
    }

    public List<FanUserInfoResponse> getFanUsers(Long userId) {
        /*粉丝用户信息列表*/
        List<FanUserInfoResponse> responses = new ArrayList<>();
        /*查询当前用户的粉丝用户信息*/
        List<FanUserInfo> fanUserInfoList = followUserMapper.getFanUserInfo(userId);
        /*查询当前用户的关注用户编号*/
        List<Long> followUserIdList = followUserMapper.getFollowUserInfo(userId).stream().map(FollowUserInfo::getUserId).collect(Collectors.toList());
        /*判断是否互相关注*/
        fanUserInfoList.stream().forEach(fanUser -> {
            FanUserInfoResponse fanUserInfoResponse = FanUserInfoResponse.builder().build();
            BeanUtils.copyProperties(fanUser, fanUserInfoResponse);
            fanUserInfoResponse.setFollowEachOther(followUserIdList.contains(fanUser.getUserId()) ? true : false);
            responses.add(fanUserInfoResponse);
        });
        return responses;
    }
}
