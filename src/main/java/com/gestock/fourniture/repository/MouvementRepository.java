package com.gestock.fourniture.repository;

import com.gestock.fourniture.model.entities.Mouvement;
import com.gestock.fourniture.model.entities.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MouvementRepository extends JpaRepository<Mouvement, Long> {
    boolean existsByCodeMouvIgnoreCase(String reference);
    Optional<Mouvement> findMouvementByCodeMouv(String nom);

    @Query("SELECT\n" +
            "    SUM(CASE WHEN m.natureMouv = 'IN' THEN m.qteMouv ELSE 0 END) -\n" +
            "    SUM(CASE WHEN m.natureMouv = 'OUT' THEN m.qteMouv ELSE 0 END) AS qteFournitureDispo\n" +
            "FROM\n" +
            "    Mouvement m\n" +
            "WHERE\n" +
            "    m.fourniture.id = :fournitureId")
    Long sumQuantiteByFournitureId(@Param("fournitureId") Long fournitureId);

    @Query("SELECT\n" +
            "    COUNT(lc.fourniture.id) as FournitureCmd\n" +
            "FROM\n" +
            "    LigneCommande lc\n" +
            "WHERE\n" +
            "    lc.etatLigneCom = 'NON-LIV'")
    Long sumQuantiteTotalCommandeNonLiv();

    @Query("SELECT\n" +
            "    COUNT(lc.fourniture.id) as FournitureNCmd\n" +
            "FROM\n" +
            "    LigneCommande lc\n" +
            "WHERE\n" +
            "    lc.etatLigneCom = 'LIVREE'")
    Long sumQuantiteTotalCommandeLiv();

    @Query("SELECT\n" +
            "    COUNT(lv.fourniture.id) as FournitureLiv\n" +
            "FROM\n" +
            "    LigneLivraison lv\n" +
            "WHERE\n" +
            "    lv.etatLivraison = 'NON-VA'")
    Long sumQuantiteTotalFournitureLiv();


    @Query("SELECT\n" +
            "    COUNT(s.fourniture.id) as FournitureSort\n" +
            "FROM\n" +
            "    Sortie s\n" +
            "WHERE\n" +
            "    s.etatSort = 'NON-VA'")
    Long sumQuantiteTotalSortie();
}
