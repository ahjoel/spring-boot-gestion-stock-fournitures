package com.gestock.fourniture.rest.ressource;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.service.CategorieService;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
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
class CategorieControllerTest {

    @InjectMocks
    private CategorieController categorieController;

    @Mock
    private CategorieService categorieService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void listeCategorie() {
        // Données de test
        CategorieDto categorie1 = CategorieDto.builder().codeCat("Categorie 1").build();
        CategorieDto categorie2 = CategorieDto.builder().codeCat("Categorie 2").build();

        List<CategorieDto> categorieList = Arrays.asList(categorie1, categorie2);

        // Configuration du comportement du service mock
        when(categorieService.listCategories()).thenReturn(categorieList);

        // Appel de la méthode du contrôleur
        List<CategorieDto> result = categorieController.listeCategorie();

        // Vérification du résultat
        assertEquals(categorieList, result);
    }
}