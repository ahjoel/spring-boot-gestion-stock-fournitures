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
@Table(name = "Fourniture")
public class Fourniture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "code")
    private String codeFour;
    @Column(name = "nom")
    private String nomFour;
    @Column(name = "mesure")
    private String mesureFour;
    @Column(name = "qte_min")
    private String qteMinFour;
    @Column(name = "date_creation")
    private LocalDate dateCreation;
    @Column(name = "date_modification")
    private LocalDate dateModification;
    @Column(name = "etat")
    private String etatFour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", nullable=false)
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
