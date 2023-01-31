package com.distribution.common.Dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class registerDto {
    @NotNull(message = "username null")
    private String username;
    @NotNull(message = "companyname null")
    private String companyname;
    @NotNull(message = "sex null")
    private int sex;
    @NotNull(message = "phone null")
    private String phone;
    @NotNull(message = "email null")
    private String email;
    @NotNull(message = "roleid null")
    private String roleid;
    @NotNull(message = "password null")
    private String password;
}
