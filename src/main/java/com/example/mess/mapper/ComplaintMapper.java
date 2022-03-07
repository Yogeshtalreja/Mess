package com.example.mess.mapper;

import com.example.mess.entity.ComplaintEntity;
import com.example.mess.model.Complaint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComplaintMapper {

    Complaint eToM(ComplaintEntity entity);

    ComplaintEntity mToE(Complaint complaint);

}
