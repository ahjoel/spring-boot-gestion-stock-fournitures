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
@Table(name = "Employe")
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "code")
    private String codeEmp;
    @Column(name = "nom")
    private String nomEmp;
    @Column(name = "prenom")
    private String prenomEmp;
    @Column(name = "service")
    private String serviceEmp;
    @Column(name = "etat")
    private String etat="ACTIVE";

    @OneToMany(mappedBy = "employe", fetch = FetchType.LAZY)
    private Collection<Commande> commandes;

    @OneToMany(mappedBy = "employe", fetch = FetchType.LAZY)
    private Collection<Sortie> sorties;
}
