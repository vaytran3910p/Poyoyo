package com.example.demo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.demo.dto.MascotDTO;
import com.example.demo.model.Mascot;

@Mapper(componentModel = "spring")
public interface MascotMapper {
	
	MascotDTO toDTO (Mascot mascot);
	
	Mascot toModel(MascotDTO dto);
	// Update Entity from DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto( MascotDTO dto, @MappingTarget Mascot entity);
}
