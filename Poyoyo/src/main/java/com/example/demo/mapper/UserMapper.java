package com.example.demo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserDTO toDTO (User user);
	
	User toModel (UserDTO dto);

	// Update Entity from DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto(UserDTO dto, @MappingTarget User entity);
}
