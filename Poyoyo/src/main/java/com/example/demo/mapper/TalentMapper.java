package com.example.demo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import com.example.demo.dto.TalentDTO;
import com.example.demo.model.Talent;

@Mapper(componentModel = "spring")
public interface TalentMapper {
	TalentDTO toDTO (Talent talent);
	
	Talent toModel(TalentDTO dto);
	// Update Entity from DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto( TalentDTO dto, @MappingTarget Talent entity);
}
