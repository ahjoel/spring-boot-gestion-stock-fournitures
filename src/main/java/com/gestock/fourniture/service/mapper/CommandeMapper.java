package com.gestock.fourniture.service.mapper;

import com.gestock.fourniture.model.dto.CommandeDto;
import com.gestock.fourniture.model.entities.Commande;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel="spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CommandeMapper {
    Commande toEntity(CommandeDto commandeDto);
    CommandeDto toDto(Commande commande);

    void copy(CommandeDto commandeDto, @MappingTarget Commande commande);
}
