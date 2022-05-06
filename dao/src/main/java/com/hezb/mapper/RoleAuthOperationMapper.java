package com.hezb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hezb.domain.RoleAuthOperation;
import com.hezb.pojo.RoleAuthOperationInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleAuthOperationMapper extends BaseMapper<RoleAuthOperation> {
    List<RoleAuthOperationInfo> getAuthOperationInfoByRoleId(@Param("roleIdList") List<Long> roleIdList);
}