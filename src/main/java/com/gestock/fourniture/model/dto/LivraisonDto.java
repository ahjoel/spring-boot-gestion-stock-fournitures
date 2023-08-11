package com.gestock.fourniture.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LivraisonDto {
    private Long id;
    private String codeLiv;
    private LocalDate dateLiv;
    private Double tvaLiv;
    private String fournisseurLiv;
}
