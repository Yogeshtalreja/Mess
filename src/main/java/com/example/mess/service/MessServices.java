package com.example.mess.service;

import com.example.mess.repository.MessRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessServices {

    private final MessRepository repository;

    public List<String> allMessesIds(){
        return repository.findAllIds();
    }

}
