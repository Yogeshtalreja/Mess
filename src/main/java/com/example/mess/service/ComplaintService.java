package com.example.mess.service;

import com.example.mess.entity.ComplaintEntity;
import com.example.mess.entity.UserEntity;
import com.example.mess.exception.GeneralException;
import com.example.mess.mapper.ComplaintMapper;
import com.example.mess.model.Complaint;
import com.example.mess.repository.ComplaintRepository;
import com.example.mess.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ComplaintService {
    private final ComplaintRepository repository;
    private final ComplaintMapper mapper;
    private final UserRepository userRepository;

    public String save(Complaint complaint) throws GeneralException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        complaint.setCreatedAt(timestamp);
        Optional<UserEntity> optional = Optional.ofNullable(userRepository.findById(complaint.getUserId())
                .orElseThrow(() -> new GeneralException("User Not Found")));
        ComplaintEntity entity = mapper.mToE(complaint);
        entity.setUser(optional.get());
        entity.setMemberUsername(optional.get().getUsername());
        entity.setMess(optional.get().getMess());
        repository.save(entity);
        return "Successful";
    }
}
