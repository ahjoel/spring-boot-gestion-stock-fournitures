package com.gestock.fourniture.service.mapper;

import com.gestock.fourniture.model.dto.*;
import com.gestock.fourniture.model.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel="spring")
public interface MouvementMapper {
    Mouvement toEntity(MouvementDto mouvementDto);
    MouvementDto toDto(Mouvement mouvement);

    Fourniture toFournitureEntity(FournitureDto fournitureDto);
    LigneLivraison toLigneLivraisonEntity(LigneLivraisonDto ligneLivraisonDto);
    Sortie toSortieEntity(SortieDto sortieDto);

    void copy(MouvementDto mouvementDto, @MappingTarget Mouvement mouvement);
}
