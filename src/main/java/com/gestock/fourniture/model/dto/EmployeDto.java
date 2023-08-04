package com.gestock.fourniture.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeDto {
    private Long id;
    private String codeEmp;
    private String nomEmp;
    private String prenomEmp;
    private String serviceEmp;
    private String etat;
}
