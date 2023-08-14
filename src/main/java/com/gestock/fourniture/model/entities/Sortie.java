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
@Table(name = "Sortie")
public class Sortie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "code")
    private String codeSort;
    @Column(name = "date_sortie")
    private LocalDate dateSort;
    @Column(name = "qte")
    private Double qteSort;
    @Column(name = "etat")
    private String etatSort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employe_id", nullable = false)
    private Employe employe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fourniture_id", nullable = false)
    private Fourniture fourniture;

    @OneToMany(mappedBy = "sortie", fetch = FetchType.LAZY)
    private Collection<Mouvement> mouvements;
}
