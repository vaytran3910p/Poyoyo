package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.model.Category;
import com.example.demo.model.Todo;
import com.example.demo.model.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CategoryService {
	private CategoryRepository repo;
	private UserRepository uRepo;
	private TodoRepository tRepo;
	private CategoryMapper mapper;
	
	public CategoryService (CategoryRepository repo, UserRepository uRepo, TodoRepository tRepo, CategoryMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
		this.uRepo = uRepo;
		this.tRepo = tRepo;
	}
	// Create service
	public CategoryDTO CreateCategoryWithoutTodos( CategoryDTO detail) {
		Category category = mapper.toModel(detail);
		User user = new User();
		user = uRepo.findByUserName(detail.getUserName())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with name:" + detail.getUserName()));
		category.setUser(user);
		category.setCreateAt(LocalDateTime.now());
		category = repo.save(category);
		return mapper.toDTO(category);
	}
	public CategoryDTO createCategoryWithTodos (CategoryDTO detail) {
		Category category = mapper.toModel(detail);
		User user = new User();
		List<Todo> todos = new ArrayList<Todo>();
		
		user = uRepo.findByUserName(detail.getUserName())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with name:" + detail.getUserName()));
		todos = detail.getTodoIds()
						.stream()
						.map(id -> tRepo.findById(id)
								.orElseThrow(() -> new ResourceNotFoundException("Not found todo with id" + id.toString())))
						.collect(Collectors.toList());
		for (Todo todo : todos) {
			todo.setCategory(category);
		}
		category.setUser(user);
		category.setTodos(todos);
		category.setCreateAt(LocalDateTime.now());
		category = repo.save(category);
		return mapper.toDTO(category);
	}
	// Read service
	public List<CategoryDTO> findAllCategory() {
		return repo.findAll()
				.stream()
				.map(mapper::toDTO)
				.toList();
	}
	public Optional<CategoryDTO> findCategoryById(Long id) {
		return repo.findById(id).map(mapper::toDTO);
	}
	// Update service
	public CategoryDTO updateCategory(Long id, CategoryDTO detail) {
		Category updateCat = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		User user = updateCat.getUser();
		if (!user.getUserName().equals(detail.getUserName())) {
			user = uRepo.findByUserName(detail.getUserName())
					.orElseThrow(() -> new ResourceNotFoundException("User not found with name:" + detail.getUserName()));
			updateCat.setUser(user);
		}
		updateCat.setName(detail.getName());
		updateCat.setDescription(detail.getDescription());
		updateCat.setColorCode(detail.getColorCode());
		updateCat = repo.save(updateCat);
		return mapper.toDTO(updateCat);
	}
	
	
}
