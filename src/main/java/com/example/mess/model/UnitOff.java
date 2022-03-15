package com.example.mess.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UnitOff {

    private Integer id;
    private Timestamp date;
    private Boolean breakfast;
    private Boolean lunch;
    private Boolean dinner;
    private Integer userId;

}
