package com.example.mess.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class Complaint {

    private Integer id;
    private Date date;
    private String message;
    private String type;
    private String feedBackType;
    private String complaintSubject;
    private Timestamp createdAt;
    private Integer userId;

}
