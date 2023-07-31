package com.gestock.fourniture.repository;

import com.gestock.fourniture.model.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    boolean existsByCodeComIgnoreCase(String reference);
}
