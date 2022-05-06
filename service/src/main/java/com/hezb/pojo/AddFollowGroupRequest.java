package com.hezb.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddFollowGroupRequest {

    /**
     * 关注组名称
     */
    @NotNull(message = "关注组名称不能为空")
    private String name;
}
