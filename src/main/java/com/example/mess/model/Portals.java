package com.example.mess.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class Portals {

    private Timestamp date;
    private String message;
    private String type;
    private String feedBackType;
    private String complaintSubject;
    private Integer userId;

}
