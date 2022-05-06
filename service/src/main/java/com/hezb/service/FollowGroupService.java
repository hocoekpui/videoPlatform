package com.hezb.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hezb.constant.FollowGroupConstant;
import com.hezb.domain.FollowGroup;
import com.hezb.exception.enums.FollowGroupExceptionEnum;
import com.hezb.mapper.FollowGroupMapper;
import com.hezb.pojo.AddFollowGroupRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class FollowGroupService {

    private final FollowGroupMapper followGroupMapper;

    public FollowGroupService(FollowGroupMapper followGroupMapper) {
        this.followGroupMapper = followGroupMapper;
    }

    public Integer addGroup(Long userId, AddFollowGroupRequest request) {
        /*判断新增分组是否已经存在*/
        FollowGroupExceptionEnum.FOLLOW_GROUP_EXIST.assertTrue(!followGroupMapper.exists(Wrappers.<FollowGroup>lambdaQuery().eq(FollowGroup::getUserId, userId).eq(FollowGroup::getName, request.getName())));
        return followGroupMapper.insert(FollowGroup.builder().userId(userId).name(request.getName()).type(FollowGroupConstant.CUSTOM_FOLLOW_GROUP_TYPE).createTime(new Date()).build());
    }

    public List<FollowGroup> getGroup(Long userId) {
        return followGroupMapper.selectList(Wrappers.<FollowGroup>lambdaQuery().eq(FollowGroup::getUserId, userId).or(item -> item.in(FollowGroup::getType, Arrays.asList(FollowGroupConstant.SPECIAL_FOLLOW_GROUP_TYPE, FollowGroupConstant.SILENT_FOLLOW_GROUP_TYPE, FollowGroupConstant.DEFAULT_FOLLOW_GROUP_TYPE))));
    }
}
