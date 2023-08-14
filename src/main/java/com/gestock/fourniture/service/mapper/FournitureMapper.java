package com.gestock.fourniture.service.mapper;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.model.dto.FournitureDto;
import com.gestock.fourniture.model.entities.Fourniture;
import com.gestock.fourniture.model.entities.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;


@Mapper(componentModel="spring")
public interface FournitureMapper {
    Fourniture toEntity(FournitureDto fournitureDto);
    FournitureDto toDto(Fourniture fourniture);

    Categorie toCategoryEntity(CategorieDto categorieDto);

    void copy(FournitureDto fournitureDto, @MappingTarget Fourniture fourniture);
}
