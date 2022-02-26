package com.example.mess.service;


import com.example.mess.exception.GeneralException;
import com.example.mess.mapper.UserMapper;
import com.example.mess.model.LoginRequest;
import com.example.mess.model.User;
import com.example.mess.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public User addUser(User model){
        return mapper.eToM(repository.save(mapper.mToE(model)));
    }

    public User login(LoginRequest request) throws GeneralException {
        return repository.findByEmailAndPassword(request.getEmail(),request.getPassword())
                .map(mapper::eToM).orElseThrow(
                        ()-> new GeneralException("Invalid Credentials"));
    }
}
