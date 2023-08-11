package com.gestock.fourniture.repository;

import com.gestock.fourniture.model.entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
    boolean existsBycodeLivIgnoreCase(String reference);
}
