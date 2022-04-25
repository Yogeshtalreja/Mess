package com.example.mess.service;

import com.example.mess.entity.CookEntity;
import com.example.mess.entity.MessEntity;
import com.example.mess.entity.UnitOffEntity;
import com.example.mess.exception.GeneralException;
import com.example.mess.model.CookDashboard;
import com.example.mess.model.CookLoginResponse;
import com.example.mess.model.LoginRequest;
import com.example.mess.repository.CookRepository;
import com.example.mess.repository.MessRepository;
import com.example.mess.repository.UnitOffRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CookService {

    private final CookRepository repository;
    private final MessRepository messRepository;
    private final UnitOffRepository unitOffRepository;

    public CookLoginResponse cookLogin(LoginRequest request) throws GeneralException {
        Optional<CookEntity> entity = repository.findByUsernameAndPassword(request.getEmail(), request.getPassword());

        if (entity.isEmpty()){
            throw new GeneralException("Cook Not Found");
        }
        Optional<MessEntity> messEntity = messRepository.findByCookUsername(entity.get().getUsername());
        if (!messEntity.isEmpty()){
            return new CookLoginResponse(entity.get().getUsername(), messEntity.get().getId());
        }
        return new CookLoginResponse(entity.get().getUsername(),null);
    }

    public List<CookDashboard> dashboardAPI(String cookId) throws GeneralException {
        Optional<CookEntity> optional = repository.findById(cookId);
        if (optional.isEmpty()){
            throw new GeneralException("Cook With This ID not Found");
        }

        Timestamp beforeTime = new Timestamp(System.currentTimeMillis());
        beforeTime.setSeconds(1);
        beforeTime.setMinutes(0);
        beforeTime.setHours(0);

        Timestamp afterTime = new Timestamp(System.currentTimeMillis());
        afterTime.setHours(23);
        afterTime.setMinutes(59);
        afterTime.setSeconds(59);

        Optional<MessEntity> messEntity = messRepository.findByCookUsername(optional.get().getUsername());
        if (messEntity.isEmpty()){
            return new ArrayList<>();
        }

        List<UnitOffEntity> unitOffEntities = unitOffRepository.findByDateBetweenAndMessId(beforeTime,afterTime,messEntity.get().getId());
        List<CookDashboard> dashboardList = new ArrayList<>();

        unitOffEntities.stream()
                .forEach(unitOffEntity -> {
                    dashboardList.add(new CookDashboard(unitOffEntity.getBreakfast(),
                            unitOffEntity.getLunch(),
                            unitOffEntity.getDinner(),
                            unitOffEntity.getUser().getUsername()));
                });
        return dashboardList;
    }

}
