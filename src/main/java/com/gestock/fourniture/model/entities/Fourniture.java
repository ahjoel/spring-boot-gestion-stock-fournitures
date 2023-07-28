package com.gestock.fourniture.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fourniture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codeFour;
    private String nomFour;
    private String mesureFour;
    private String qteMinFour;
    private LocalDate dateCreation;
    private LocalDate dateModification;
    private String etatFour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idCategorie", nullable=false)
    private Categorie categorie;

    @OneToMany(mappedBy = "fourniture", fetch = FetchType.LAZY)
    private Collection<LigneCommande>  ligneCommandes;

    @OneToMany(mappedBy = "fourniture", fetch = FetchType.LAZY)
    private Collection<LigneLivraison> ligneLivraisons;

    @OneToMany(mappedBy = "fourniture", fetch = FetchType.LAZY)
    private Collection<Sortie> sorties;

    @OneToMany(mappedBy = "fourniture", fetch = FetchType.LAZY)
    private Collection<Mouvement> mouvements;
}
