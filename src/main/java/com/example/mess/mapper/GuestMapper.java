package com.example.mess.mapper;

import com.example.mess.entity.GuestEntity;
import com.example.mess.entity.UnitOffEntity;
import com.example.mess.model.Guest;
import com.example.mess.model.UnitOff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    GuestEntity mToE(Guest model);

    Guest eToM(GuestEntity entity);

    UnitOffEntity mToE(UnitOff model);
}
