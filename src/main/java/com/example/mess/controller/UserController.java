package com.example.mess.controller;


import com.example.mess.exception.GeneralException;
import com.example.mess.model.LoginRequest;
import com.example.mess.model.User;
import com.example.mess.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @PostMapping("/addUser")
    User addUser(@RequestBody User model){return service.addUser(model);}

    @PostMapping("/login")
    User login(@RequestBody LoginRequest request) throws GeneralException{return service.login(request);}
}
