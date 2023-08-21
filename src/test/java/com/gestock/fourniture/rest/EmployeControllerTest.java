package com.gestock.fourniture.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.rest.ressource.EmployeController;
import com.gestock.fourniture.service.EmployeService;
import com.gestock.fourniture.service.mapper.EmployeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EmployeController.class)
class EmployeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeMapper employeMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeService employeService;

    private List<EmployeDto> employeDtoList;

    @BeforeEach
    void setUp() {
        // Initialisation des données de test pour la liste d'employés
        employeDtoList = Arrays.asList(
                EmployeDto.builder().codeEmp("EMP001").nomEmp("Doe").prenomEmp("John").serviceEmp("ADMINISTRATIF").build(),
                EmployeDto.builder().codeEmp("EMP002").nomEmp("Smith").prenomEmp("Jane").serviceEmp("ADMINISTRATIF").build()
        );
    }



    @Test
    void testListeEmploye() throws Exception{
        // Mock du service pour renvoyer une liste d'employés fictive
        when(employeService.listEmployes()).thenReturn(employeDtoList);

        // Effectuer la requête HTTP GET sur "/api/employes" et vérifier le résultat
        mockMvc.perform(get("/api/employes"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(employeDtoList)));
    }

    @Test
    void testEnregistrerEmploye() throws Exception {
        // Créez un objet EmployeDto de test
        EmployeDto employeDto = EmployeDto.builder()
                .codeEmp("EMP003")
                .nomEmp("Doe")
                .prenomEmp("Jane")
                .serviceEmp("RH")
                .build();

        // Mock du service pour renvoyer l'employé enregistré
        when(employeService.ajouterEmploye(any(EmployeDto.class))).thenReturn(employeDto.getId());

        // Effectuez la requête HTTP POST pour enregistrer l'employé
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employe/addemp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(employeMapper.toEntity(employeDto))))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("employeDto.getId()" ));
    }

    @Test
    void afficherEmploye() {
    }

    @Test
    void modifierEmploye() {
    }

    @Test
    void deleteEmploye() {
    }
}