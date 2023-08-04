package com.gestock.fourniture.repository;

import com.gestock.fourniture.model.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    boolean existsByCodeCatIgnoreCase(String reference);

    Optional<Categorie> findCategorieByCodeCat(String nom);
    Optional<Categorie> findCategorieById(Long id);
}
