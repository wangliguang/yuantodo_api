package com.guang.yuantodo.requestbody;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RequestBodyUser {

    private static final long serialVersionUID = 1L;

    @Pattern(regexp = "^\\d{11}$", message = "请输入正确的手机号")
    @NotNull(message = "mobile不能为空")
    private String mobile;

    @NotNull(message = "password不能为空")
    private String password;
}

