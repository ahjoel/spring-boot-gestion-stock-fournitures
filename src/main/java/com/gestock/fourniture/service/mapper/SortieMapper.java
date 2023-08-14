package com.gestock.fourniture.service.mapper;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.model.dto.FournitureDto;
import com.gestock.fourniture.model.dto.SortieDto;
import com.gestock.fourniture.model.entities.Categorie;
import com.gestock.fourniture.model.entities.Employe;
import com.gestock.fourniture.model.entities.Fourniture;
import com.gestock.fourniture.model.entities.Sortie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel="spring")
public interface SortieMapper {
    Sortie toEntity(SortieDto sortieDto);
    SortieDto toDto(Sortie sortie);

    Fourniture toFournitureEntity(FournitureDto fournitureDto);
    Employe toEmployeEntity(EmployeDto employeDto);

    void copy(SortieDto sortieDto, @MappingTarget Sortie sortie);
}
