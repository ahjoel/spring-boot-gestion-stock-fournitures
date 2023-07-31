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
@Table(name = "Categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "code")
    private String codeCat;
    @Column(name = "nom")
    private String nomCat;
    @Column(name = "description")
    private String descriptionCat;

    @OneToMany(mappedBy = "categorie", fetch = FetchType.LAZY)
    private Collection<Fourniture>  fournitures;
}
