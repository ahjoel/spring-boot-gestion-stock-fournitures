package com.gestock.fourniture.repository;

import com.gestock.fourniture.model.entities.LigneCommande;
import com.gestock.fourniture.model.entities.LigneLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LigneLivraisonRepository extends JpaRepository<LigneLivraison, Long> {


}
