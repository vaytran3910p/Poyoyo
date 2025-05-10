package com.example.demo.controller;

import java.awt.print.Pageable;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.dto.PageResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/poyoyo/users")
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@GetMapping("/testApi")
	public String testApi() {
		return "SUCCESS";
	}
	
	// CRUD API
	
	// Create user
	@PostMapping
	public ResponseEntity<UserDTO> addUser( @RequestBody UserDTO user) { 
    	UserDTO saveUser = userService.createUser(user);
    	return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}
	
	// Read user data
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers(){ 
		return ResponseEntity.ok(userService.findAll());
	}
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return ResponseEntity.ok(userDTO);
    }
    
    @GetMapping("/listUser")
    public PageResponse<UserDTO> getListUserWithPagination (
    		@RequestParam (defaultValue = "0" ) int page, 
    		@RequestParam (defaultValue = "5") int size) {
    	// service return pageable type
    	Page<UserDTO> data = userService.getListUserWithPagination(page, size);
    	PageResponse<UserDTO> response = new PageResponse<UserDTO>(
    			data.getContent(),
    			data.getNumber(),
    			data.getTotalPages(),
    			data.getTotalElements(),
    			data.getSize());
    	return response;
    }
    @GetMapping("/listValidUser")
    public ResponseEntity<List<UserDTO>> getListValidUser () {
    	List<UserDTO> result = userService.getLstUserWithCondition();
    	return ResponseEntity.ok(result);
    }
	
	// Update user
	@PutMapping("/updateUser/{Id}") 
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long Id, @RequestBody UserDTO detail) {
		return ResponseEntity.ok(userService.updateUser(Id, detail));
	}
	
	// Delete user
	@DeleteMapping("/deleteUser")
	public ResponseEntity<Void> deleteUser(@PathVariable Long Id) {
		userService.deleteUser(Id);
		return ResponseEntity.noContent().build();
	}
	
}
