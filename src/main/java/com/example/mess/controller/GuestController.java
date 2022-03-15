package com.example.mess.controller;

import com.example.mess.exception.GeneralException;
import com.example.mess.model.Guest;
import com.example.mess.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/guest")
public class GuestController {

    private final GuestService service;

    @PostMapping("/addGuest")
    Guest addGuest(@RequestBody Guest model) throws GeneralException {
        return service.addGuest(model);
    }
}
