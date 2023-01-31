package com.distribution.common.Dto.vfUser;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class updateUserDto {
    @NotNull(message = "username null")
    String username;
    @NotNull(message = "companyname null")
    String companyname;
    @NotNull(message = "useremail null")
    String useremail;
    @NotNull(message = "userphone null")
    String userphone;
    @NotNull(message = "usersex null")
    String usersex;
    @NotNull(message = "userstatus null")
    int userstatus;
}
