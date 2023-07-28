package com.gestock.fourniture.service.mapper;

import com.gestock.fourniture.model.dto.FournitureDto;
import com.gestock.fourniture.model.entities.Fourniture;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FournitureMapper {
    Fourniture toEntity(FournitureDto fournitureDto);
    FournitureDto toDto(Fourniture fourniture);

    void copy(FournitureDto fournitureDto, @MappingTarget Fourniture fourniture);
}
