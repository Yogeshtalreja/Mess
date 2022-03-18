package com.example.mess.service;

import com.example.mess.mapper.MenuMapper;
import com.example.mess.model.Menu;
import com.example.mess.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MenuService {

    private final MenuRepository repository;
    private final MenuMapper mapper;

   public List<Menu> findByMessId(String messId){
       return repository.findAllByMessId(messId)
               .stream().map(mapper::eToM).collect(Collectors.toList());
    }

}
