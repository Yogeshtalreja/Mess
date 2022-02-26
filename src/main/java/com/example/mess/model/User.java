package com.example.mess.model;

import com.example.mess.enums.UserTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class User {

    private Integer id;
    private String name;
    private String contact;
    @NotNull
    private String email;
    @NotNull
    private String password;
//    @NotNull
    private UserTypeEnum type;
}
