package com.gestock.fourniture.repository;

import com.gestock.fourniture.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPseudo(String email);
    boolean existsByPseudoIgnoreCase(String reference);
    boolean existsByEmailIgnoreCase(String reference);
}
