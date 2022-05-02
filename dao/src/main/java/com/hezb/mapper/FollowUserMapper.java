package com.hezb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hezb.domain.FollowUser;
import com.hezb.model.FanUserInfo;
import com.hezb.model.FollowUserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowUserMapper extends BaseMapper<FollowUser> {

    List<FollowUserInfo> getFollowUserInfo(Long userId);

    List<FanUserInfo> getFanUserInfo(Long followUserId);
}