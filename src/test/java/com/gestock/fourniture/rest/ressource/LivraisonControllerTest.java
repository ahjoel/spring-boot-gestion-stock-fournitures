package com.gestock.fourniture.rest.ressource;

import com.gestock.fourniture.model.dto.LigneLivraisonDto;
import com.gestock.fourniture.model.dto.LivraisonDto;
import com.gestock.fourniture.service.LigneLivraisonService;
import com.gestock.fourniture.service.LivraisonService;
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
class LivraisonControllerTest {

    @InjectMocks
    private LivraisonController livraisonController;

    @Mock
    private LivraisonService livraisonService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void listeLivraison() {
        // Données de test
        LivraisonDto livraison1 = LivraisonDto.builder().codeLiv("livraison 1").build();
        LivraisonDto livraison2 = LivraisonDto.builder().codeLiv("livraison 2").build();

        List<LivraisonDto> livraisonList = Arrays.asList(livraison1, livraison2);

        // Configuration du comportement du service mock
        when(livraisonService.listLivraisons()).thenReturn(livraisonList);

        // Appel de la méthode du contrôleur
        List<LivraisonDto> result = livraisonController.listeLivraison();

        // Vérification du résultat
        assertEquals(livraisonList, result);
    }
}