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
@Table(name = "Commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "code")
    private String codeCom;
    @Column(name = "date_commande")
    private LocalDate dateCom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employe_id", nullable=false)
    private Employe employe;

    @OneToMany(mappedBy = "commande", fetch = FetchType.LAZY)
    private Collection<LigneCommande>  ligneCommandes;
}
