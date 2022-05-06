package com.hezb.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @NotBlank(message = "注册手机号不能为空")
    private String phone;

    @NotBlank(message = "用户密码不能为空")
    private String password;

    private String email;
}
