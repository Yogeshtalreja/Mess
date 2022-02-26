package com.example.mess.mapper;


import com.example.mess.entity.UserEntity;
import com.example.mess.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User eToM(UserEntity entity);

    UserEntity mToE(User model);

}
