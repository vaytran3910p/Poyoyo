package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.TalentDTO;
import com.example.demo.mapper.TalentMapper;
import com.example.demo.repository.TalentRepository;

@Service
public class TalentService {
	private TalentMapper mapper;
	private TalentRepository repo;
	public TalentService(TalentMapper mapper, TalentRepository repo) {
		this.mapper = mapper;
		this.repo =  repo;
	}
	public List<TalentDTO> findAll () {
		return repo.findAll().stream().map(mapper::toDTO).toList();
	}
	public Optional<TalentDTO> findById(Long Id) {
		return repo.findById(Id).map(mapper::toDTO);
	}
	
}
