package com.gestock.fourniture.rest.ressource;

import com.gestock.fourniture.model.dto.LigneCommandeDto;
import com.gestock.fourniture.model.entities.LigneCommande;
import com.gestock.fourniture.service.CommandeService;
import com.gestock.fourniture.service.LigneCommandeService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class LigneCommandeControllerTest {

    @InjectMocks
    private LigneCommandeController ligneCommandeController;

    @Mock
    private LigneCommandeService ligneCommandeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void listeLigneCommande() {
        // Données de test
        LigneCommandeDto lignecommande1 = LigneCommandeDto.builder().id(1L).qteLigneCom(Double.valueOf(5)).etatLigneCom("NON-LIVREE").build();
        LigneCommandeDto lignecommande2 = LigneCommandeDto.builder().id(2L).qteLigneCom(Double.valueOf(6)).etatLigneCom("NON-LIVREE").build();

        List<LigneCommandeDto> lignecommandeList = Arrays.asList(lignecommande1, lignecommande2);

        // Configuration du comportement du service mock
        when(ligneCommandeService.listLigneCommandes()).thenReturn(lignecommandeList);

        // Appel de la méthode du contrôleur
        List<LigneCommandeDto> result = ligneCommandeController.listeLigneCommande();

        // Vérification du résultat
        assertEquals(lignecommandeList, result);
    }

    @Test
    void listeLigneCommandeLivree() {
        // Données de test
        LigneCommandeDto lignecommande1 = LigneCommandeDto.builder().id(1L).qteLigneCom(Double.valueOf(7)).etatLigneCom("NON-LIVREE").build();
        LigneCommandeDto lignecommande2 = LigneCommandeDto.builder().id(2L).qteLigneCom(Double.valueOf(8)).etatLigneCom("LIVREE").build();

        List<LigneCommandeDto> lignecommandeList = Arrays.asList(lignecommande1, lignecommande2);

        // Configuration du comportement du service mock
        when(ligneCommandeService.trierLigneCommandeParFournitureNonLivre()).thenReturn(lignecommandeList);

        // Appel de la méthode du contrôleur
        List<LigneCommandeDto> result = ligneCommandeController.listeLigneCommandeLivree();

        // Vérification du résultat
        assertEquals(lignecommandeList, result);
    }
}