package com.example.demo.repository.CustomRepo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.MascotDTO;

@Repository
public interface MascotRepositoryCustom {

	List<MascotDTO> findAllValidMascot();

	Page<MascotDTO> findAllValidMascotWithPage(Pageable page);

}
