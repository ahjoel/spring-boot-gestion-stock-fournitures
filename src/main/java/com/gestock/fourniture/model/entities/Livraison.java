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
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codeLiv;
    private Date dateLiv;
    private Double tvaLiv;
    private String fournisseurLiv;

    @OneToMany(mappedBy = "livraison", fetch = FetchType.LAZY)
    private Collection<LigneLivraison> ligneLivraisons;

}
