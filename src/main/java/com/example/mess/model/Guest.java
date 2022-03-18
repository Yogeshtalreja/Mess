package com.example.mess.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Guest {

    private Integer id;
    private Boolean inBreakfast;
    private Timestamp date;
    private Boolean inLunch;
    private Boolean inDinner;
    private Integer userId;

}
