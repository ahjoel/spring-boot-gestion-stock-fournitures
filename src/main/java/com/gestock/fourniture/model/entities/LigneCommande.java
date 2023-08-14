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
@Table(name = "LigneCommande")
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "qte")
    private Double qteLigneCom;
    @Column(name = "etat")
    private String etatLigneCom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fourniture_id", nullable = false)
    private Fourniture fourniture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @OneToMany(mappedBy = "lignecommande", fetch = FetchType.LAZY)
    private Collection<LigneLivraison> ligneLivraisons;
}
