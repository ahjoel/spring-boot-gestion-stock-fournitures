package com.gestock.fourniture.repository;

import com.gestock.fourniture.model.entities.Fourniture;
import com.gestock.fourniture.model.entities.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SortieRepository extends JpaRepository<Sortie, Long> {
    boolean existsByCodeSortIgnoreCase(String reference);
    Optional<Sortie> findSortieByCodeSort(String nom);

}
