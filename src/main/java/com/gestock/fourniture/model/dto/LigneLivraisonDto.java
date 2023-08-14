package com.gestock.fourniture.model.dto;

import com.gestock.fourniture.model.entities.Fourniture;
import com.gestock.fourniture.model.entities.LigneCommande;
import com.gestock.fourniture.model.entities.Livraison;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LigneLivraisonDto {
    private Long id;
    private Double qteLivraison;
    private Double prixLivraison;
    private String etatLivraison;
    private LivraisonDto livraison;
    private LigneCommandeDto lignecommande;
    private FournitureDto fourniture;
}
