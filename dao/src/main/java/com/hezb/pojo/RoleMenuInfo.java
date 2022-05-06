package com.hezb.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuInfo {

    /**
     * 关联记录主键
     */
    private Long id;

    /**
     * 角色主键
     */
    private Long roleId;

    /**
     * 菜单主键
     */
    private Long menuId;

    /**
     * 菜单编号
     */
    private String code;

    /**
     * 菜单名称
     */
    private String name;
}
