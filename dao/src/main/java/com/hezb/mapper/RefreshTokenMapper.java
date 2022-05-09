package com.hezb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hezb.domain.RefreshToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefreshTokenMapper extends BaseMapper<RefreshToken> {
}