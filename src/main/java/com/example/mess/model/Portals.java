package com.example.mess.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Portals {

    private Date date;
    private String message;
    private String type;
    private String feedBackType;
    private String complaintSubject;
    private Integer userId;

}
