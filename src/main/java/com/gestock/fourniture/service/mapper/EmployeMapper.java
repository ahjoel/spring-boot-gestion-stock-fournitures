package com.gestock.fourniture.service.mapper;

import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.model.entities.Employe;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring")
public interface EmployeMapper {
    Employe toEntity(EmployeDto employeDto);
    EmployeDto toDto(Employe employe);

    void copy(EmployeDto employeDto, @MappingTarget Employe employe);
}
