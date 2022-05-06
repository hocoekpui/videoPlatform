package com.hezb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hezb.domain.RoleMenu;
import com.hezb.pojo.RoleMenuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    List<RoleMenuInfo> getMenuByRoleId(@Param("roleIdList") List<Long> roleIdList);
}