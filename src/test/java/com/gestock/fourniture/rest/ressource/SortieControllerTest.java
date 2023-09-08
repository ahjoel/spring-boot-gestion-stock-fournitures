package com.gestock.fourniture.rest.ressource;

import com.gestock.fourniture.model.dto.MouvementDto;
import com.gestock.fourniture.model.dto.SortieDto;
import com.gestock.fourniture.service.MouvementService;
import com.gestock.fourniture.service.SortieService;
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
class SortieControllerTest {

    @InjectMocks
    private SortieController sortieController;

    @Mock
    private SortieService sortieService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void listeSortie() {
        // Données de test
        SortieDto sortie1 = SortieDto.builder().id(1L).codeSort("Sortie 1").build();
        SortieDto sortie2 = SortieDto.builder().id(1L).codeSort("Sortie 2").build();

        List<SortieDto> sortieList = Arrays.asList(sortie1, sortie2);

        // Configuration du comportement du service mock
        when(sortieService.listSorties()).thenReturn(sortieList);

        // Appel de la méthode du contrôleur
        List<SortieDto> result = sortieController.listeSortie();

        // Vérification du résultat
        assertEquals(sortieList, result);
    }
}