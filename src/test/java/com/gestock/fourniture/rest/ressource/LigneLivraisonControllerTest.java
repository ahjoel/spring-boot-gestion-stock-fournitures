package com.gestock.fourniture.rest.ressource;

import com.gestock.fourniture.model.dto.LigneCommandeDto;
import com.gestock.fourniture.model.dto.LigneLivraisonDto;
import com.gestock.fourniture.model.entities.LigneLivraison;
import com.gestock.fourniture.service.LigneCommandeService;
import com.gestock.fourniture.service.LigneLivraisonService;
import org.junit.Before;
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
class LigneLivraisonControllerTest {

    @InjectMocks
    private LigneLivraisonController ligneLivraisonController;

    @Mock
    private LigneLivraisonService ligneLivraisonService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void listeLigneLivraison() {
        // Données de test
        LigneLivraisonDto lignelivraison1 = LigneLivraisonDto.builder().id(1L).qteLivraison(Double.valueOf(9)).etatLivraison("VA").build();
        LigneLivraisonDto lignelivraison2 = LigneLivraisonDto.builder().id(1L).qteLivraison(Double.valueOf(10)).etatLivraison("VA").build();

        List<LigneLivraisonDto> lignelivraisonList = Arrays.asList(lignelivraison1, lignelivraison2);

        // Configuration du comportement du service mock
        when(ligneLivraisonService.listLigneLivraisons()).thenReturn(lignelivraisonList);

        // Appel de la méthode du contrôleur
        List<LigneLivraisonDto> result = ligneLivraisonController.listeLigneLivraison();

        // Vérification du résultat
        assertEquals(lignelivraisonList, result);
    }
}