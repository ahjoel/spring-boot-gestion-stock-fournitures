package com.gestock.fourniture.service.mapper;

import com.gestock.fourniture.model.dto.CommandeDto;
import com.gestock.fourniture.model.dto.FournitureDto;
import com.gestock.fourniture.model.dto.LigneCommandeDto;
import com.gestock.fourniture.model.entities.Commande;
import com.gestock.fourniture.model.entities.Fourniture;
import com.gestock.fourniture.model.entities.LigneCommande;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;


@Mapper(componentModel="spring")
public interface LigneCommandeMapper {
    LigneCommande toEntity(LigneCommandeDto ligneCommandeDto);
    LigneCommandeDto toDto(LigneCommande ligneCommande);

    Commande toCommandeEntity(CommandeDto commandeDto);
    Fourniture toFournitureEntity(FournitureDto fournitureDto);

    void copy(LigneCommandeDto ligneCommandeDto, @MappingTarget LigneCommande ligneCommande);
}
