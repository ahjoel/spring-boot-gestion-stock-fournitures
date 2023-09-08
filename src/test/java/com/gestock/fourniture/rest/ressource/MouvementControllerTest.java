package com.gestock.fourniture.rest.ressource;

import com.gestock.fourniture.model.dto.LigneLivraisonDto;
import com.gestock.fourniture.model.dto.MouvementDto;
import com.gestock.fourniture.service.LigneLivraisonService;
import com.gestock.fourniture.service.MouvementService;
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
class MouvementControllerTest {

    @InjectMocks
    private MouvementController mouvementController;

    @Mock
    private MouvementService mouvementService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void listeMouvement() {
        // Données de test
        MouvementDto mouvement1 = MouvementDto.builder().id(1L).codeMouv("Mouvement 1").build();
        MouvementDto mouvement2 = MouvementDto.builder().id(1L).codeMouv("Mouvement 2").build();

        List<MouvementDto> mouvementList = Arrays.asList(mouvement1, mouvement2);

        // Configuration du comportement du service mock
        when(mouvementService.listMouvements()).thenReturn(mouvementList);

        // Appel de la méthode du contrôleur
        List<MouvementDto> result = mouvementController.listeMouvement();

        // Vérification du résultat
        assertEquals(mouvementList, result);
    }
}