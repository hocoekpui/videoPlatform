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
@TableName(value = "auth_operation")
public class AuthOperation {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 元素名称
     */
    @TableField(value = "element_name")
    private String elementName;

    /**
     * 元素编号
     */
    @TableField(value = "element_code")
    private String elementCode;

    /**
     * 权限类型，0：可点击，1：可见
     */
    @TableField(value = "operation_type")
    private String operationType;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}