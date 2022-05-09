package com.hezb.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hezb.constant.RoleCodeConstant;
import com.hezb.domain.UserRole;
import com.hezb.mapper.RoleAuthOperationMapper;
import com.hezb.mapper.RoleMenuMapper;
import com.hezb.mapper.UserRoleMapper;
import com.hezb.pojo.RoleAuthOperationInfo;
import com.hezb.pojo.RoleMenuInfo;
import com.hezb.pojo.UserAuthResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAuthService {

    private final UserRoleMapper userRoleMapper;
    private final RoleAuthOperationMapper roleAuthOperationMapper;
    private final RoleMenuMapper roleMenuMapper;

    public UserAuthService(UserRoleMapper userRoleMapper, RoleAuthOperationMapper roleAuthOperationMapper, RoleMenuMapper roleMenuMapper) {
        this.userRoleMapper = userRoleMapper;
        this.roleAuthOperationMapper = roleAuthOperationMapper;
        this.roleMenuMapper = roleMenuMapper;
    }

    public UserAuthResponse getUserAuthorities(Long userId) {
        List<Long> roleIdList = userRoleMapper.selectList(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId)).stream().map(UserRole::getRoleId).collect(Collectors.toList());
        List<RoleAuthOperationInfo> roleAuthOperationInfoList = roleAuthOperationMapper.getAuthOperationInfoByRoleId(roleIdList);
        List<RoleMenuInfo> roleMenuInfoList = roleMenuMapper.getMenuByRoleId(roleIdList);
        return UserAuthResponse.builder().roleAuthOperationList(roleAuthOperationInfoList).roleMenuList(roleMenuInfoList).build();
    }

    public Integer addDefaultRole(Long userId) {
        return userRoleMapper.insert(UserRole.builder().userId(userId).roleId(RoleCodeConstant.DEFAULT_ROLE_ID).createTime(new Date()).build());
    }
}
