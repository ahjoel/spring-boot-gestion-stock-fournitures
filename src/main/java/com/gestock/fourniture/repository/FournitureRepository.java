package com.gestock.fourniture.repository;

import com.gestock.fourniture.model.entities.Fourniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournitureRepository extends JpaRepository<Fourniture, Long> {
    boolean existsByCodeFourIgnoreCase(String reference);
}
