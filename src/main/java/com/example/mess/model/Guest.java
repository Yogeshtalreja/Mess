package com.example.mess.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Guest {

    private Integer id;
    private Boolean inBreakfast;
    private Boolean inLunch;
    private Boolean inDinner;
    private Integer userId;

}
