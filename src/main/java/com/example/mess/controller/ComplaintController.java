package com.example.mess.controller;

import com.example.mess.constants.Url;
import com.example.mess.exception.GeneralException;
import com.example.mess.model.Complaint;
import com.example.mess.service.ComplaintService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/complaint")
public class ComplaintController {

    private final ComplaintService service;

    @PostMapping(Url.SAVE)
    String save(@RequestBody Complaint complaint) throws GeneralException {
        return service.save(complaint);
    }

}
