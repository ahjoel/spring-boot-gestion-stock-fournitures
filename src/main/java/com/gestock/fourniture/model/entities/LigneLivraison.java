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
public class LigneLivraison {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double qteLivraison;
    private Double prixLivraison;
    private String etatLivraison;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idlivraison", nullable = false)
    private Livraison livraison;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idlignecommande", nullable = false)
    private LigneCommande ligneCommande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idfourniture", nullable = false)
    private Fourniture fourniture;

    @OneToMany(mappedBy = "ligneLivraison", fetch = FetchType.LAZY)
    private Collection<Mouvement> mouvements;

}
