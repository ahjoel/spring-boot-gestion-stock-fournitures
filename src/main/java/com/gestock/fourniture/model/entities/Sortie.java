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
public class Sortie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codeSort;
    private Date dateSort;
    private Double qteSort;
    private String etatSort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idemploye", nullable = false)
    private Employe employe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idfourniture", nullable = false)
    private Fourniture fourniture;

    @OneToMany(mappedBy = "sortie", fetch = FetchType.LAZY)
    private Collection<Mouvement> mouvements;
}
