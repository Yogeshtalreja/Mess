package com.example.mess.controller;

import com.example.mess.constants.Url;
import com.example.mess.model.Menu;
import com.example.mess.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService service;

    @GetMapping(Url.FIND_MENU_BY_MESS_ID)
    List<Menu> findByMessId(@PathVariable("messId") String messId){
        return service.findByMessId(messId);
    }

}
