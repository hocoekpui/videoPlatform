package com.hezb.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUpdateRequest {

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 签名
     */
    private String sign;

    /**
     * 性别，0：男，1：女，2：未知
     */
    private String gender;

    /**
     * 生日
     */
    private String birth;
}
