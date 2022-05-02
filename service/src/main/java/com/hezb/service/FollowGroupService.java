package com.hezb.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hezb.domain.FollowGroup;
import com.hezb.mapper.FollowGroupMapper;
import org.springframework.stereotype.Service;

@Service
public class FollowGroupService {

    private final FollowGroupMapper followGroupMapper;

    public FollowGroupService(FollowGroupMapper followGroupMapper) {
        this.followGroupMapper = followGroupMapper;
    }

    public FollowGroup getByGroupType(String type) {
        return followGroupMapper.selectOne(Wrappers.<FollowGroup>lambdaQuery().eq(FollowGroup::getType, type));
    }

    public FollowGroup getByGroupId(String id) {
        return followGroupMapper.selectOne(Wrappers.<FollowGroup>lambdaQuery().eq(FollowGroup::getId, id));
    }
}
