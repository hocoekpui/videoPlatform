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
@TableName(value = "role_menu")
public class RoleMenu {
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
     * 菜单主键
     */
    @TableField(value = "menu_id")
    private Integer menuId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
}