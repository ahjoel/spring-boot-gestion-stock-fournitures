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
@Table(name = "Livraison")
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "code")
    private String codeLiv;
    @Column(name = "date_livraison")
    private LocalDate dateLiv;
    @Column(name = "tva")
    private Double tvaLiv;
    @Column(name = "fournisseur")
    private String fournisseurLiv;

    @OneToMany(mappedBy = "livraison", fetch = FetchType.LAZY)
    private Collection<LigneLivraison> ligneLivraisons;

}
