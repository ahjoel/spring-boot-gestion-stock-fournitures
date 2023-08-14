package com.gestock.fourniture.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Mouvement")
public class Mouvement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "code")
    private String codeMouv;
    @Column(name = "date_mouvement")
    private LocalDate datemouv;
    @Column(name = "date_inventaire")
    private LocalDate dateInventaire;
    @Column(name = "qte")
    private Double qteMouv;
    @Column(name = "nature")
    private String natureMouv;
    @Column(name = "etat")
    private String etatMouv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lignelivraison_id", nullable = true)
    private LigneLivraison lignelivraison;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sortie_id", nullable = true)
    private Sortie sortie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fourniture_id", nullable = false)
    private Fourniture fourniture;

}
