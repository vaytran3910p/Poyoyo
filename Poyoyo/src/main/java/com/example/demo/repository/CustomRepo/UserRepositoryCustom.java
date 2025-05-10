package com.example.demo.repository.CustomRepo;

import java.util.List;

import com.example.demo.dto.UserDTO;

public interface UserRepositoryCustom {

	List<UserDTO> getLstUserWithCompleteInfo();

}
