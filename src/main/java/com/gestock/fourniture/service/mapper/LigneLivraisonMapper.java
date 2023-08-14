package com.gestock.fourniture.service.mapper;

import com.gestock.fourniture.model.dto.FournitureDto;
import com.gestock.fourniture.model.dto.LigneCommandeDto;
import com.gestock.fourniture.model.dto.LigneLivraisonDto;
import com.gestock.fourniture.model.dto.LivraisonDto;
import com.gestock.fourniture.model.entities.Fourniture;
import com.gestock.fourniture.model.entities.LigneCommande;
import com.gestock.fourniture.model.entities.LigneLivraison;
import com.gestock.fourniture.model.entities.Livraison;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;


@Mapper(componentModel="spring")
public interface LigneLivraisonMapper {
    LigneLivraison toEntity(LigneLivraisonDto livraisonDto);
    LigneLivraisonDto toDto(LigneLivraison ligneLivraison);

    Livraison toLivraisonEntity(LivraisonDto livraisonDto);
    LigneCommande toLigneCommandeEntity(LigneCommandeDto ligneCommandeDto);
    Fourniture toFournitureEntity(FournitureDto fournitureDto);

    void copy(LigneLivraisonDto livraisonDto, @MappingTarget LigneLivraison livraison);
}
