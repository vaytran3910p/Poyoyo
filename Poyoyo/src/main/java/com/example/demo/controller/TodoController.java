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
import com.example.demo.dto.TodoDTO;
import com.example.demo.service.TodoService;


@RestController
@RequestMapping("/poyoyo/todos")
public class TodoController {
	
	@Autowired
	public TodoService service;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@PostMapping("/create")
	public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO dto) {
		TodoDTO result = service.createTodo(dto);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/findTodoById/{id}")
	public ResponseEntity<TodoDTO> findById (@PathVariable Long id) {
		TodoDTO result = service.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found todo with this id"));
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/findAllTodos")
	public ResponseEntity<List<TodoDTO>> findAllTodos() {
		List<TodoDTO> data = service.findAllTodos();
		return ResponseEntity.ok(data);
	}
	@PutMapping("/updateTodo/{id}")
	public ResponseEntity<TodoDTO> updateTodos(@PathVariable Long id, @RequestBody TodoDTO detail) {
		TodoDTO result = service.updateTodos(id, detail);
		return ResponseEntity.ok(result);
	}
}