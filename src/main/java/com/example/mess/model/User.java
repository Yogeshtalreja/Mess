package com.example.mess.model;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class User {

    private Integer id;
    private String username;
    private String contact;
    @NotNull
    private String email;
    @NotNull
    private String password;



}
