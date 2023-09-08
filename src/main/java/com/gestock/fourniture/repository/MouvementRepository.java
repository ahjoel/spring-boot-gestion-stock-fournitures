package com.gestock.fourniture.repository;

import com.gestock.fourniture.model.entities.Mouvement;
import com.gestock.fourniture.model.entities.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
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
            "    SUM(lv.qteLivraison) as FournitureLiv\n" +
            "FROM\n" +
            "    LigneLivraison lv\n" +
            "WHERE\n" +
            "    lv.etatLivraison = 'VA'")
    Long sumQuantiteTotalFournitureLiv();


    @Query("SELECT\n" +
            "    SUM(s.qteSort) as FournitureSort\n" +
            "FROM\n" +
            "    Sortie s\n" +
            "WHERE\n" +
            "    s.etatSort = 'VA'")
    Long sumQuantiteTotalSortie();

    @Query("SELECT f.id AS id, f.nomFour, \n" +
            "                            sum(case \n" +
            "                                    when m.datemouv < :dateDeb \n" +
            "                                    then case m.natureMouv when 'OUT' then -1 else 1 end\n" +
            "                                    else 0 \n" +
            "                                end * qteMouv) AS st_init,\n" +
            "                            sum(case\n" +
            "                                    when m.datemouv BETWEEN :dateDeb AND :dateFin\n" +
            "                                    AND m.natureMouv = 'IN' then qteMouv\n" +
            "                                    else 0 \n" +
            "                            end) AS qt_e,\n" +
            "                            sum(case \n" +
            "                                    when m.datemouv BETWEEN :dateDeb AND :dateFin\n" +
            "                                    AND m.natureMouv = 'OUT' then qteMouv\n" +
            "                                    else 0 \n" +
            "                                end) AS qt_s, \n" +
            "                            sum(case m.natureMouv when 'OUT' then -1 else 1 end * qteMouv) AS st_fin\n" +
            "                            FROM Mouvement m, Fourniture f\n" +
            "                            WHERE m.fourniture.id=f.id \n" +
            "                            AND m.etatMouv <= 'ACTIVE' \n" +
            "                            AND m.datemouv <= :dateFin\n" +
            "                            GROUP BY f.id\n" +
            "                            ORDER BY f.nomFour " +
            "")
    List<Object[]> getSituationStockSurPeriode(@Param("dateDeb") LocalDate dateDeb, @Param("dateFin") LocalDate dateFin);
}
