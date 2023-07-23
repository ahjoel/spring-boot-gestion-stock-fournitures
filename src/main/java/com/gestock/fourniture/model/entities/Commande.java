package com.gestock.fourniture.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codeCom;
    private Date dateCom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idEmploye", nullable=false)
    private Employe employe;

    @OneToMany(mappedBy = "commande", fetch = FetchType.LAZY)
    private Collection<LigneCommande>  ligneCommandes;
}
