package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/poyoyo/categories")
public class CategoryController {	
	
	@Autowired
	private CategoryService service;
	
	@GetMapping("/hello") 
	public String hello() {
		return "hello";
	}
	
	// CRUD Controller
	// Create Controller
	@PostMapping("/addCatWithoutTodos")
	public ResponseEntity<CategoryDTO> craetCategoryWithoutTodos(@RequestBody CategoryDTO dto) {
		CategoryDTO result = service.CreateCategoryWithoutTodos(dto);
		return ResponseEntity.ok(result);
	}
	// add categories with todos
	@PostMapping("/addCatWithTodos")
	public ResponseEntity<CategoryDTO> createCategoryWithTodos(@RequestBody CategoryDTO dto) {
		CategoryDTO result = service.createCategoryWithTodos(dto);
		return ResponseEntity.ok(result);
	}
	
	// Read controller
	@GetMapping("/findAllCategories")
	public ResponseEntity<List<CategoryDTO>> findAllCategory () {
		List<CategoryDTO> results = service.findAllCategory();
		return ResponseEntity.ok(results);
	}
	@GetMapping("/findCategoryById/{id}")
	public ResponseEntity<CategoryDTO> findById( @PathVariable Long id) {
		CategoryDTO result = service.findCategoryById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));;
		return ResponseEntity.ok(result);
	}
	// Update Controller
	@PutMapping("/updateCategoryWithoutTodos/{id}")
	public ResponseEntity<CategoryDTO> updateCategoryWithoutTodos (
			@PathVariable Long id, 
			@RequestBody CategoryDTO detail) {
		CategoryDTO result = service.updateCategory(id, detail);
		return ResponseEntity.ok(result);
	}
}	
