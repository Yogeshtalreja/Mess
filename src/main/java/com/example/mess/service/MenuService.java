package com.example.mess.service;

import com.example.mess.mapper.MenuMapper;
import com.example.mess.model.Menu;
import com.example.mess.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MenuService {

    private final MenuRepository repository;
    private final MenuMapper mapper;

   public Menu findByMessId(String messId){
        return mapper.eToM(repository.findByMessId(messId));
    }

}
