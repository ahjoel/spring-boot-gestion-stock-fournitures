package com.gestock.fourniture.service.mapper;

import com.gestock.fourniture.model.dto.LivraisonDto;
import com.gestock.fourniture.model.entities.Livraison;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;


@Mapper(componentModel="spring")
public interface LivraisonMapper {
    Livraison toEntity(LivraisonDto livraisonDto);
    LivraisonDto toDto(Livraison livraison);
    void copy(LivraisonDto livraisonDto, @MappingTarget Livraison livraison);
}
