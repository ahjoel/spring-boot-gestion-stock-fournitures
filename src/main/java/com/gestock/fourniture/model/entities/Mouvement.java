package com.gestock.fourniture.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mouvement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codeMouv;
    private Date datemouv;
    private Date dateInventaire;
    private Double qteMouv;
    private String natureMouv;
    private String etatMouv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idlignelivraison", nullable = true)
    private LigneLivraison ligneLivraison;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idsortie", nullable = true)
    private Sortie sortie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idfourniture", nullable = false)
    private Fourniture fourniture;


}
