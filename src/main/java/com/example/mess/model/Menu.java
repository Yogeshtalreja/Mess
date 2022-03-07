package com.example.mess.model;

import com.example.mess.enums.Days;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {
    private Integer id;
    private Days day;
    private String breakfast;
    private String lunch;
    private String dinner;
}
