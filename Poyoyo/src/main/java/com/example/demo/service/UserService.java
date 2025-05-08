package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	private UserMapper mapper;
	private UserRepository repo;
	public UserService (UserMapper mapper, UserRepository repo) {
		this.mapper = mapper;
		this.repo = repo;
	}
	public List<UserDTO> findAll () {
		return repo.findAll().stream().map(mapper::toDTO).toList();
	}
	public Optional<UserDTO> findById (Long Id) {
		return repo.findById(Id).map(mapper::toDTO);
	}
}
