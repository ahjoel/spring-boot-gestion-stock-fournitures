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
@Table(name = "LigneLivraison")
public class LigneLivraison {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "qte")
    private Double qteLivraison;
    @Column(name = "prix")
    private Double prixLivraison;
    @Column(name = "etat")
    private String etatLivraison;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livraison_id", nullable = false)
    private Livraison livraison;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lignecommande_id", nullable = false)
    private LigneCommande lignecommande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fourniture_id", nullable = false)
    private Fourniture fourniture;

    @OneToMany(mappedBy = "lignelivraison", fetch = FetchType.LAZY)
    private Collection<Mouvement> mouvements;

}
