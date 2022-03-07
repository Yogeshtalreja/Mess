package com.example.mess.controller;

import com.example.mess.service.MessServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/mess")
public class MessController {

    private final MessServices service;
    private final static String FIND_ALL_MESS_IDS = "/findAllMessIds";

    @GetMapping(FIND_ALL_MESS_IDS)
    List<String> findAllMessIds(){
        return service.allMessesIds();
    }
}
