package com.example.mess.service;

import com.example.mess.repository.MessRepository;
import com.example.mess.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessServices {

    private final MessRepository repository;
    private final UserRepository userRepository;

    public List<String> allMessesIds(){
        return repository.findAllIds();
    }

    public Integer allUsersByMessId(String messId){
        return userRepository.countDistinctByMessId(messId);
    }
}
