package com.hezb.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName(value = "refresh_token")
public class RefreshToken {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 用户主键
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 刷新令牌
     */
    @TableField(value = "refresh_token")
    private String refreshToken;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
}