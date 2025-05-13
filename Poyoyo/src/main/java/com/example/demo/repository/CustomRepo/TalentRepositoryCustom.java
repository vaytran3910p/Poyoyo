package com.example.demo.repository.CustomRepo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.TalentDTO;

public interface TalentRepositoryCustom {

	List<TalentDTO> findTalentIsValid();

	Page<TalentDTO> findTalentIsValidWithPagination(Pageable page);

}
