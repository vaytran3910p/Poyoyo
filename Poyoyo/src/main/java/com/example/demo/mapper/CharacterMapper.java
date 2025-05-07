package com.example.demo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import com.example.demo.dto.CharacterDTO;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
	CharacterDTO toDTO (Character character);
	
	Character toModel(CharacterDTO dto);
	// Update Entity from DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto( CharacterDTO dto, @MappingTarget Character entity);
}
