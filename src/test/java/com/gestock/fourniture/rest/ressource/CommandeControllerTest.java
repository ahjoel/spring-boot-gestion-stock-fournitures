package com.gestock.fourniture.rest.ressource;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.model.dto.CommandeDto;
import com.gestock.fourniture.service.CategorieService;
import com.gestock.fourniture.service.CommandeService;
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
class CommandeControllerTest {

    @InjectMocks
    private CommandeController commandeController;

    @Mock
    private CommandeService commandeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void listeCommande() {
        // Données de test
        CommandeDto commande1 = CommandeDto.builder().codeCom("Commande 1").build();
        CommandeDto commande2 = CommandeDto.builder().codeCom("Commande 2").build();

        List<CommandeDto> commandeList = Arrays.asList(commande1, commande2);

        // Configuration du comportement du service mock
        when(commandeService.listCommandes()).thenReturn(commandeList);

        // Appel de la méthode du contrôleur
        List<CommandeDto> result = commandeController.listeCommande();

        // Vérification du résultat
        assertEquals(commandeList, result);
    }
}