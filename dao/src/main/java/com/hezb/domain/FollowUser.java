package com.hezb.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@TableName(value = "follow_user")
public class FollowUser {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户编号
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 关注用户编号
     */
    @TableField(value = "follow_user_id")
    private Long followUserId;

    /**
     * 关注分组编号
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
}