package com.example.mess.controller;


import com.example.mess.constants.Url;
import com.example.mess.exception.GeneralException;
import com.example.mess.model.LoginRequest;
import com.example.mess.model.TimeStamp;
import com.example.mess.model.User;
import com.example.mess.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    public final static String GET_ALL_MEMBERS = "/allMembers";
    public final static String LOGIN = "/login";

    @PostMapping(Url.SAVE)
    String addUser(@RequestBody User model)throws GeneralException{return service.addUser(model);}

    @PostMapping(LOGIN)
    User login(@RequestBody LoginRequest request) throws GeneralException{return service.login(request);}

    @GetMapping(Url.FIND_BY_ID)
    User findById(@PathVariable("id") Integer id) throws GeneralException {return service.findById(id);}

    @DeleteMapping(Url.DELETE_BY_ID)
    void deleteById(@PathVariable("id") Integer id)throws GeneralException{service.deleteById(id);}

    @PostMapping("/getReport/{userId}")
    public void getReportByUserId(@RequestBody TimeStamp timeStamp,  @PathVariable("userId") Integer userId, HttpServletResponse response) throws IOException, GeneralException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_"+userId+".pdf";
        response.setHeader(headerKey,headerValue);
        service.export(response,userId,timeStamp);
    }
}
