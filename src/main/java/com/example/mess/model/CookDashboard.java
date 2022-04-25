package com.example.mess.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CookDashboard {

    private Boolean breakFast;
    private Boolean lunch;
    private Boolean dinner;
    private String userName;

}
