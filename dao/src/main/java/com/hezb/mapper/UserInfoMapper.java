package com.hezb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hezb.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}