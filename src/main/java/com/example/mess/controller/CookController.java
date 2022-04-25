package com.example.mess.controller;


import com.example.mess.exception.GeneralException;
import com.example.mess.model.CookDashboard;
import com.example.mess.model.CookLoginResponse;
import com.example.mess.model.LoginRequest;
import com.example.mess.service.CookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cook")
public class CookController {

    private final CookService service;

    @PostMapping("/login")
    public CookLoginResponse login(@RequestBody LoginRequest request) throws GeneralException {
        return service.cookLogin(request);
    }

    @GetMapping("/dashboardAPI/{cookId}")
    public List<CookDashboard> dashboardAPI(@PathVariable("cookId") String cookId) throws GeneralException {
        return service.dashboardAPI(cookId);
    }
}
