package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.MascotDTO;
import com.example.demo.mapper.MascotMapper;
import com.example.demo.repository.MascotRepository;

@Service
public class MascotService {
	private MascotMapper mapper;
	private MascotRepository repo;
	
	public MascotService(MascotMapper mapper, MascotRepository repo) {
		this.mapper = mapper;
		this.repo = repo;
	}
	
	public List<MascotDTO> findAllMascot() { 
		List<MascotDTO>  result = new ArrayList<MascotDTO>();
		result = repo.findAll().stream().map(mapper::toDTO).toList();
		return result;
	}
	public Optional<MascotDTO> findById(Long Id) {
		return repo.findById(Id).map(mapper::toDTO);
	}
}

