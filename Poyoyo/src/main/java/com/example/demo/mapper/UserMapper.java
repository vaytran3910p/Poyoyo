package com.example.demo.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.eNum.RoleType;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(target = "roles", expression = "java(mapRolesToStrings(user.getRoles()))")
	UserDTO toDTO (User user);
	
	@Mapping(target = "roles", expression = "java(mapStringsToRoles(dto.getRoles()))")
	User toModel (UserDTO dto);

	// Update Entity from DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto(UserDTO dto, @MappingTarget User entity);
    
    default Set<String> mapRolesToStrings(Set<Role> roles) {
    	return roles.stream()
    			.map(role -> role.getRole().name())
    			.collect(Collectors.toSet());
    }
    default Set<Role> mapStringsToRoles(Set<String> roleNames) {
    	return roleNames.stream()
    			.map(name -> {
    				Role role = new Role();
    				role.setRole(RoleType.valueOf(name));
    				return role;
    			})
    			.collect(Collectors.toSet());
    }
}
