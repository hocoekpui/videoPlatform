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
public class UnFollowUserRequest {

    /**
     * 被取消关注人编号
     */
    @NotNull(message = "被取消关注人编号不能为空")
    private Long followUserId;
}
