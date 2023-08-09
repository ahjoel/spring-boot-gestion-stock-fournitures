package com.gestock.fourniture.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandeDto {
    public Long id;
    private String codeCom;
    private LocalDate dateCom;
    private EmployeDto employe;
}
