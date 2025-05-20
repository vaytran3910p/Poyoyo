package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.dto.MascotDTO;
import com.example.demo.mapper.MascotMapper;
import com.example.demo.model.Mascot;
import com.example.demo.model.Talent;
import com.example.demo.repository.MascotRepository;
import com.example.demo.repository.TalentRepository;

@Service
public class MascotService {
	private MascotMapper mapper;
	private MascotRepository repo;
	
	public MascotService(MascotMapper mapper, MascotRepository repo) {
		this.mapper = mapper;
		this.repo = repo;
	}
	
	
	// create service
	public MascotDTO createMascot (MascotDTO dto) {
		Mascot mascot = mapper.toModel(dto);
		mascot.setStatus("ACT");
		mascot.setIsActive(1);
		mascot.setIsDelete(0);
		mascot = repo.save(mascot);
		return mapper.toDTO(mascot);
	}
	// read service
	public List<MascotDTO> findAllMascot() { 
		List<MascotDTO>  result = new ArrayList<MascotDTO>();
		result = repo.findAll().stream().map(mapper::toDTO).toList();
		return result;
	}
	public Optional<MascotDTO> findById(Long Id) {
		return repo.findById(Id).map(mapper::toDTO);
	}
	public Page<MascotDTO> findAllMascotWithPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return repo.findAll(pageable).map(mapper::toDTO);
	}
	public List<MascotDTO> findAllValidMascot(){
		List<MascotDTO> result = repo.findAllValidMascot();
		return result;
	}
	public Page<MascotDTO> findAllValidMascotWithPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<MascotDTO> data = repo.findAllValidMascotWithPage(pageable);
		return data;
	}
	// update service
	public MascotDTO updateMascot(Long id, MascotDTO detail) {
		Mascot mascot = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mascot not found"));
		mascot.setMascotCode(detail.getMascotCode());
		mascot.setMascotName(detail.getMascotName());
		mascot = repo.save(mascot);
		return mapper.toDTO(mascot);
	}
	// delete service
	public void deleteMascot(Long id) {
		repo.deleteById(id);
	}
} 

