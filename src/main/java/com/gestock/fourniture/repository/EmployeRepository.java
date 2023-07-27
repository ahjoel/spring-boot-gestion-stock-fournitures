package com.gestock.fourniture.repository;

import com.gestock.fourniture.model.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
    boolean existsByCodeEmpIgnoreCase(String reference);

    Optional<Employe> findEmployeByCodeEmp(String nom);
}
