package com.dds.demo.service.mapper;

import com.dds.demo.dto.UserDTO;
import com.dds.demo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {
}
