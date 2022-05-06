package com.hezb.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuthOperationInfo {

    /**
     * 关联记录主键
     */
    private Long id;

    /**
     * 角色主键
     */
    private Long roleId;

    /**
     * 元素主键
     */
    private Long elementOperationId;

    /**
     * 元素名称
     */
    private String elementName;

    /**
     * 元素编号
     */
    private String elementCode;

    /**
     * 操作权限类型
     */
    private String operationType;
}
