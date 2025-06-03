package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.Optional;

import org.hibernate.ResourceClosedException;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.dto.TodoDTO;
import com.example.demo.mapper.TodoMapper;
import com.example.demo.model.Category;
import com.example.demo.model.Todo;
import com.example.demo.model.User;
import com.example.demo.model.eNum.Priority;
import com.example.demo.model.eNum.TodoStatus;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.StringUtils;

@Service
public class TodoService {
	
	private TodoRepository repo;
	private TodoMapper mapper;
	private UserRepository userRepo;
	private CategoryRepository catRepo;
	
	public TodoService(TodoRepository repo, TodoMapper mapper, UserRepository userRepo, CategoryRepository catRepo) {
		this.repo = repo;
		this.mapper = mapper;
		this.userRepo = userRepo;
		this.catRepo = catRepo;
	}
	//CRUD service
	// create service
	public TodoDTO createTodo(TodoDTO dto) {
		
		Todo todo = mapper.toModel(dto);
		
		User user = userRepo.findByUserName(dto.getUserName())
				.orElseThrow(() -> new ResourceNotFoundException("Not found user with name:" + dto.getUserName()));
		Category category = catRepo.findByName(dto.getCategoryName())
				.orElseThrow(() -> new ResourceNotFoundException("Not found category with name: " + dto.getCategoryName()));
		category.getTodos().add(todo);
		todo.setUser(user);
		todo.setCategory(category);
		todo.setStatus(TodoStatus.valueOf(dto.getStatus()));
		todo.setPriority(Priority.valueOf(dto.getPriority()));
		todo.setCreateAt(LocalDateTime.now());
		todo = repo.save(todo);
		return mapper.toDTO(todo);
	}
	// read service
	public List<TodoDTO> findAllTodos() {
		return repo.findAll().stream().map(mapper::toDTO).toList();
	}
	
	public Optional<TodoDTO> findById (Long id) {
		return repo.findById(id).map(mapper::toDTO);
	}
	// update 
	public TodoDTO updateTodos(Long id,TodoDTO dto) {
		Todo todo = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todos not found"));
		String userName = todo.getUser().getUserName();
		if(!StringUtils.isNullOrEmpty(userName) && !userName.equals(dto.getUserName())) {
			User user = userRepo.findByUserName(dto.getUserName())
					.orElseThrow(()-> new ResourceNotFoundException("Not found user with name: " + dto.getUserName()));
			todo.setUser(user);
		}
		Category oldCat = todo.getCategory();
		if (!StringUtils.isNullOrEmpty(dto.getCategoryName()) && !oldCat.getName().equals(dto.getCategoryName())) {
			Category newCat = catRepo.findByName(dto.getCategoryName())
					.orElseThrow(() -> new ResourceNotFoundException("Not found category with name: " + dto.getCategoryName()));
			oldCat.getTodos().remove(todo);
			newCat.getTodos().add(todo);
			todo.setCategory(newCat);	
		}
		todo = repo.save(todo);
		return mapper.toDTO(todo);
	}
}
