package com.gestock.fourniture.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilisateurDto {
    private String code;
    private String nom;
    private String prenom;
    private String service;
    private String etat="ACTIVE";
}
