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
@TableName(value = "role_auth_operation")
public class RoleAuthOperation {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色主键
     */
    @TableField(value = "role_id")
    private Integer roleId;

    /**
     * 元素主键
     */
    @TableField(value = "element_operation_id")
    private Integer elementOperationId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
}