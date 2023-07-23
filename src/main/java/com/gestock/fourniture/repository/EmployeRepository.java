package com.gestock.fourniture.repository;

import com.gestock.fourniture.model.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
    boolean existsByCodeEmpIgnoreCase(String reference);
}
