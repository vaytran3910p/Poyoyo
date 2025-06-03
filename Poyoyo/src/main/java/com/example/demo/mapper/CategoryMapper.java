package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Todo;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	
	@Mappings({
		@Mapping(source = "user.userName", target = "userName"),
		@Mapping(source = "todos", target = "todoIds", qualifiedByName = "mapTodoListToIds")
	})
	CategoryDTO toDTO(Category category);
	
	@Mapping(target = "user", ignore = true)
	@Mapping(target = "todos", ignore = true)
	Category toModel (CategoryDTO dto);
	
	@Named("mapTodoListToIds")
	static List<Long> mapTodoListToIds(List<Todo> todos) {
		if (todos == null) return null;
		return todos.stream().map(Todo::getId).collect(Collectors.toList());
	}
}
