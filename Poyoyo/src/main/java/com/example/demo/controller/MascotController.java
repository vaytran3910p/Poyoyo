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
import org.springframework.web.client.ResourceAccessException;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.dto.MascotDTO;
import com.example.demo.dto.PageResponse;
import com.example.demo.service.MascotService;

@RestController
@RequestMapping("/poyoyo/mascots")
public class MascotController {
	
	@Autowired
	private MascotService service;
	
	@GetMapping("/testApi")
	public String testApi() {
		return "SUCCESS";
	}
	// CRUD controller
	// Create controller
	@PostMapping("/create")
	public ResponseEntity<MascotDTO> createMascot(@RequestBody MascotDTO dto) {
		MascotDTO result = service.createMascot(dto);
		return ResponseEntity.ok(result);
	}
	// Read controller
	@GetMapping("/findAll")
	public ResponseEntity<List<MascotDTO>> findAllMascot() {
		List<MascotDTO> data = service.findAllMascot();
		return ResponseEntity.ok(data);
	}
	@GetMapping("/findAllWithPage")
	public PageResponse<MascotDTO> findAllMascotWithPage(
			@RequestParam (defaultValue = "0") int page, 
			@RequestParam (defaultValue = "2") int size) {
		Page<MascotDTO> data = service.findAllMascotWithPage(page, size);
		PageResponse<MascotDTO> response =  new PageResponse<MascotDTO>(
				data.getContent(), 
				data.getNumber(), 
				data.getTotalPages(), 
				data.getTotalElements(), 
				data.getSize());
		return response ;
	}
	@GetMapping("/findById/{id}")
	public ResponseEntity<MascotDTO> findById (@PathVariable Long id) {
		MascotDTO result = service.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not found Mascot"));
		return ResponseEntity.ok(result);
	}
	@GetMapping("/findAllValid")
	public ResponseEntity<List<MascotDTO>> findAllValidMascot(){
		List<MascotDTO> result = service.findAllValidMascot();
		return ResponseEntity.ok(result);
	}
	@GetMapping("/findAllValidWithPage")
	public PageResponse<MascotDTO> findAllValidMascotWithPage(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2" ) int size) {
		Page<MascotDTO> data = service.findAllMascotWithPage(page, size);
		return new PageResponse<MascotDTO>(
				data.getContent(), 
				data.getNumber(), 
				data.getTotalPages(), 
				data.getTotalElements(), 
				data.getSize());
	}
	// Update controller
	@PutMapping("/update") 
	public ResponseEntity<MascotDTO> updateMascot(Long id, MascotDTO detail){
		MascotDTO result = service.updateMascot(id, detail);
		return ResponseEntity.ok(result);
	}
	// Delete controller
	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteMascot(Long id) {
		service.deleteMascot(id);
		return ResponseEntity.noContent().build();
	}
}
