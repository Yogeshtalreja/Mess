package com.example.mess.service;

import com.example.mess.entity.GuestEntity;
import com.example.mess.entity.UserEntity;
import com.example.mess.exception.GeneralException;
import com.example.mess.mapper.GuestMapper;
import com.example.mess.model.Guest;
import com.example.mess.repository.GuestRepository;
import com.example.mess.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuestService {

    private final GuestRepository repository;
    private final UserRepository userRepository;
    private final GuestMapper mapper;


    public Guest addGuest(Guest model) throws GeneralException {
        Optional<UserEntity> user = userRepository.findById(model.getUserId());
        if (user.isEmpty()){
            throw new GeneralException("User with this ID Not Found");
        }
        GuestEntity entity = mapper.mToE(model);
        entity.setUser(user.get());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        entity.setCreatedAt(timestamp);
        return mapper.eToM(repository.save(entity));
    }

}
