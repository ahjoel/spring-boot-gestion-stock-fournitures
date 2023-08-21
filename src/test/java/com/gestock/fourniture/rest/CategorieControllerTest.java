package com.gestock.fourniture.rest;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.rest.ressource.CategorieController;
import com.gestock.fourniture.service.CategorieService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
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
        List<CategorieDto> categories = new ArrayList<>(); // Créez des catégories de test
        Mockito.when(categorieService.listCategories()).thenReturn(categories);

        List<CategorieDto> result = categorieController.listeCategorie();

        // Vérifiez que le résultat correspond aux catégories de test
        assertEquals(categories, result);
    }

    @Test
    void enregistrerCategorie() {
    }
}