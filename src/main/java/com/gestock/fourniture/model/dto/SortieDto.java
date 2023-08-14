package com.gestock.fourniture.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SortieDto {
    private Long id;
    private String codeSort;
    private LocalDate dateSort;
    private Double qteSort;
    private String etatSort;
    private EmployeDto employe;
    private FournitureDto fourniture;
}
