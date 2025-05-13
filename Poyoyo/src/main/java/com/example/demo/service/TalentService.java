package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.dto.TalentDTO;
import com.example.demo.mapper.TalentMapper;
import com.example.demo.model.Talent;
import com.example.demo.repository.TalentRepository;

@Service
public class TalentService {
	private TalentMapper mapper;
	private TalentRepository repo;
	public TalentService(TalentMapper mapper, TalentRepository repo) {
		this.mapper = mapper;
		this.repo =  repo;
	}
	
	
	// CRUD service
	// create service
	public TalentDTO createTalent(TalentDTO info) {
		Talent talent = mapper.toModel(info);
		talent.setStatus("ACT");
		talent.setIsActive(1);
		talent.setIsDelete(0);
		talent = repo.save(talent);
		return mapper.toDTO(talent);
	}
	// read service
	public List<TalentDTO> findAll () {
		return repo.findAll().stream().map(mapper::toDTO).toList();
	}
	public Optional<TalentDTO> findById(Long Id) {
		return repo.findById(Id).map(mapper::toDTO);
	}
	public Page<TalentDTO> findAllWithPage (int page, int size) {
		Pageable pagination = PageRequest.of(page, size);
		return repo.findAll(pagination).map(mapper::toDTO);
	}
	public List<TalentDTO> findWithCustomQuery() {
		List<TalentDTO> result = repo.findTalentIsValid();
		return result;
	}
	public Page<TalentDTO> findWithCustomQueryAndPage(int page, int size){
		Pageable pagination =  PageRequest.of(page, size);
		Page<TalentDTO> result = repo.findTalentIsValidWithPagination(pagination);
		return result;
	}
	// update service
	public TalentDTO updateSerice (Long id, TalentDTO detail) {
		Talent talent = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		talent.setTalentCode(detail.getTalentCode());
		talent.setTalentName(detail.getTalentName());
		talent.setCategory(detail.getCategory());
		talent = repo.save(talent);
		return mapper.toDTO(talent);
	}
	// delete service
	public void deleteServic(Long id) {
		Talent talent = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		repo.delete(talent);
	}
	
	
}
