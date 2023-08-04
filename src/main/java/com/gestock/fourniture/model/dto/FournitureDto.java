package com.gestock.fourniture.model.dto;

import com.gestock.fourniture.model.entities.Categorie;
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
public class FournitureDto {
    private Long id;
    private String codeFour;
    private String nomFour;
    private String mesureFour;
    private String qteMinFour;
    private LocalDate dateCreation;
    private LocalDate dateModification;
    private String etatFour;
    private CategorieDto categorie;
}
