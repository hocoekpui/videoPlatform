package com.hezb.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleInfo {

    /**
     * 关联记录编号
     */
    private String id;

    /**
     * 角色名稱
     */
    private String roleName;

    /**
     * 角色編碼
     */
    private String roleCode;
}
