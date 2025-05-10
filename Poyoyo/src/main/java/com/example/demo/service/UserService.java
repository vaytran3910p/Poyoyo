package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PageResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
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
	public UserDTO createUser(UserDTO dto) { 
//		User saveUser = repo.save(dto);
//		return mapper.toDTO(saveUser);
		
		User saveuser = mapper.toModel(dto);
		saveuser.setStatus("ACT");
		saveuser.setIsActive(1);
		saveuser.setIsDelete(0);
		saveuser = repo.save(saveuser);
		return mapper.toDTO(saveuser);
	}
	public UserDTO updateUser(Long Id, UserDTO dto) {
		User user = repo.findById(Id).orElseThrow();
		user.setUserName(dto.getUserName());
		user.setPassWord(dto.getPassWord());
		User saveUser = repo.save(user);
		return mapper.toDTO(saveUser);
		
	}
	public void deleteUser(Long id) {
		repo.deleteById(id);
	}
	
	public Page<UserDTO> getListUserWithPagination(int page,int size) {
		Pageable pagination = PageRequest.of(page, size);
		return repo.findAll(pagination).map(mapper::toDTO);
	}
	public List<UserDTO> getLstUserWithCondition() {
		return repo.getLstUserWithCompleteInfo();
	}
}
