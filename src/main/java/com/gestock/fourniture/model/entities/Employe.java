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
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codeEmp;
    private String nomEmp;
    private String prenomEmp;
    private String serviceEmp;
    private String etat="ACTIVE";

    @OneToMany(mappedBy = "employe", fetch = FetchType.LAZY)
    private Collection<Commande> commandes;

    @OneToMany(mappedBy = "employe", fetch = FetchType.LAZY)
    private Collection<Sortie> sorties;
}
