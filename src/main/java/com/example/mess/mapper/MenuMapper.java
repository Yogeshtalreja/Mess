package com.example.mess.mapper;

import com.example.mess.entity.MenuEntity;
import com.example.mess.model.Menu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    Menu eToM(MenuEntity entity);
}
