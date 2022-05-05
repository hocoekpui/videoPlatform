package com.hezb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMomentReleaseRequest {

    /**
     * 动态类型
     */
    @NotBlank(message = "动态类型不能为空")
    private String type;

    /**
     * 内容详情编号
     */
    @NotNull(message = "内容详情编号不能为空")
    private Long contentId;
}
