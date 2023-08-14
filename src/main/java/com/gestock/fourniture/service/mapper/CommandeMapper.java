package com.gestock.fourniture.service.mapper;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.model.dto.CommandeDto;
import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.model.entities.Categorie;
import com.gestock.fourniture.model.entities.Commande;
import com.gestock.fourniture.model.entities.Employe;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;


@Mapper(componentModel="spring")
public interface CommandeMapper {
    Commande toEntity(CommandeDto commandeDto);
    CommandeDto toDto(Commande commande);
    Employe toEmployeEntity(EmployeDto employeDto);
    void copy(CommandeDto commandeDto, @MappingTarget Commande commande);
}
