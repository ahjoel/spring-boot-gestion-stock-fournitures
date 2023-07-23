package com.gestock.fourniture.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double qteLigneCom;
    private String etatLigneCom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idfourniture", nullable = false)
    private Fourniture fourniture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcommande", nullable = false)
    private Commande commande;

    @OneToMany(mappedBy = "ligneCommande", fetch = FetchType.LAZY)
    private Collection<LigneLivraison> ligneLivraisons;
}
