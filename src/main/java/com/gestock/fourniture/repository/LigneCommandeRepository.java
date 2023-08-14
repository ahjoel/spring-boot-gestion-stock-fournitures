package com.gestock.fourniture.repository;

import com.gestock.fourniture.model.entities.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
    @Query("select lc from LigneCommande lc where lc.etatLigneCom = 'NON-LIV'")
    List<LigneCommande> trierLigneCommandeParFournitureNonLivre();

    @Transactional
    @Modifying
    @Query("update LigneCommande lc set lc.etatLigneCom = 'LIVREE' where lc.id = :id")
    void updateEtatToLivreeById(Long id);
}
