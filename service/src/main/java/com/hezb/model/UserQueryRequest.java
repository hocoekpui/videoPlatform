package com.hezb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryRequest {

    /**
     * 页码
     */
    @NotNull(message = "页码不能为空")
    private Integer pageNo;

    /**
     * 页面大小
     */
    @NotNull(message = "页面大小不能为空")
    private Integer pageSize;

    /**
     * 昵称
     */
    private String nickName;
}
