package com.gestock.fourniture.repository;

import com.gestock.fourniture.model.entities.Mouvement;
import com.gestock.fourniture.model.entities.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MouvementRepository extends JpaRepository<Mouvement, Long> {
    boolean existsByCodeMouvIgnoreCase(String reference);
    Optional<Mouvement> findMouvementByCodeMouv(String nom);
}
