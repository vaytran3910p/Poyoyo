package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.example.demo.dto.TalentDTO;
import com.example.demo.model.Talent;
import com.example.demo.service.TalentService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/poyoyo/talents")
public class TalentController {
	
	@Autowired
	private TalentService talentService;
	
	
	@GetMapping("/testApi")
	public String testApi() {
		return "SUCCESS";
	}
	
	// CRUD controller 
	// create controller
	@PostMapping("/createTalent")
	public ResponseEntity<TalentDTO> createTalent(@RequestBody TalentDTO info) {
		TalentDTO result = talentService.createTalent(info); 
		return ResponseEntity.ok(result);
	}
	
	// read controller 
	@GetMapping("/findAll")
	public ResponseEntity<List<TalentDTO>> findAllTalent (){ 
		List<TalentDTO> result= talentService.findAll();
		return ResponseEntity.ok(result); 
	}
	@GetMapping("/findById/{id}")
	public ResponseEntity<TalentDTO> findTalentById( @PathVariable Long id) {
		TalentDTO data = talentService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Talent not found"));
		return ResponseEntity.ok(data);
	}
	@GetMapping("/findWithPage")
	public PageResponse<TalentDTO> findTalentIsValid (
			@RequestParam (defaultValue = "0") int page, 
			@RequestParam (defaultValue = "5") int size
			) {
		Page<TalentDTO> data = talentService.findAllWithPage(page, size);
		PageResponse<TalentDTO> response = new PageResponse<TalentDTO>(
				data.getContent(), 
				data.getNumber(), 
				data.getTotalPages(),
				data.getTotalElements(),
				data.getSize());
		return response;
	}
	@GetMapping("/findCompleteTalentInfo")
	public ResponseEntity<List<TalentDTO>> findWithCustomQuery(
			@RequestParam int page,
			@RequestParam int size) {
		List<TalentDTO> result = talentService.findWithCustomQuery();
		return ResponseEntity.ok(result);
	}
	@GetMapping("/findCompleteInfoWithPage")
	public PageResponse<TalentDTO> findCompleteInfoWithPage(
			@RequestParam (defaultValue = "0") int page,
			@RequestParam (defaultValue = "1") int size
			) {
		Page<TalentDTO> data = talentService.findWithCustomQueryAndPage(page, size);
		PageResponse<TalentDTO> response = new PageResponse<TalentDTO>(
				data.getContent(), 
				data.getNumber(), 
				data.getTotalPages(), 
				data.getTotalElements(), 
				data.getSize());
		return response;
	}

	// update controller
	@PutMapping("/updateInfo/{id}")
	public ResponseEntity<TalentDTO> updateTalentInfo (
			@RequestParam Long id, @RequestBody TalentDTO detail) {
		TalentDTO data = talentService.updateSerice(id, detail);
		return ResponseEntity.ok(data);
	}
	// delete controller
	@DeleteMapping("/deleteInfo/{id}")
	public ResponseEntity<Void> DeleteTalentInfo( @PathVariable Long id) {
		return ResponseEntity.noContent().build();
	}
	
}
