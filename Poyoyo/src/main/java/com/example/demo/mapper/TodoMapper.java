package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Todo;
import com.example.demo.model.User;

@Mapper(componentModel = "spring")
public interface TodoMapper {
	
	@Mapping(source = "user.userName", target = "userName")
	@Mapping(source = "category.name", target = "categoryName")
	TodoDTO toDTO (Todo todo) ; 
	
	@Mapping(target ="user", ignore = true)
	@Mapping(target ="category", ignore = true)
	Todo toModel (TodoDTO dto);
	
}
