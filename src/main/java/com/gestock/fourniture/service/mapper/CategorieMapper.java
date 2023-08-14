package com.gestock.fourniture.service.mapper;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.model.entities.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring")
public interface CategorieMapper {
    Categorie toEntity(CategorieDto categorieDto);
    CategorieDto toDto(Categorie categorie);

    void copy(CategorieDto categorieDto, @MappingTarget Categorie categorie);
}
