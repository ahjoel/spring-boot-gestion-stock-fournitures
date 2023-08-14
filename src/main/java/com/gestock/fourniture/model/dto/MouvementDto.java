package com.gestock.fourniture.model.dto;

import com.gestock.fourniture.model.entities.LigneLivraison;
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
public class MouvementDto {
    private Long id;
    private String codeMouv;
    private LocalDate datemouv;
    private LocalDate dateInventaire;
    private Double qteMouv;
    private String natureMouv;
    private String etatMouv;

    private LigneLivraisonDto lignelivraison;
    private SortieDto sortie;
    private FournitureDto fourniture;
}
