package com.gestock.fourniture.rest.ressource;

import com.gestock.fourniture.model.dto.SortieDto;
import com.gestock.fourniture.service.SortieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SortieController {
    @Autowired
    SortieService sortieService;

    @RequestMapping(path="sorties", method = RequestMethod.GET)
    public List<SortieDto> listeSortie() {
        return sortieService.listSorties();
    }

    @RequestMapping(value="sortie/addsor", method = RequestMethod.POST)
    public Long enregistrerSortie(@RequestBody SortieDto sortieDto){
        return sortieService.ajouterSortie(sortieDto);
    }

    private SortieDto getSortieDtoIdExist(Long id) {
        SortieDto sortieDto = SortieDto.builder().id(id).build();
        SortieDto sortieDtoFound = sortieService.getSortieById(sortieDto);

        if (sortieDtoFound == null) {
            throw new RuntimeException("Code 257 : le code sortie envoye n'existe pas");
        }
        return sortieDtoFound;
    }

    @RequestMapping(value="sortie/{id}", method = RequestMethod.GET)
    public SortieDto afficherSortie(@PathVariable("id") Long id) {
        return getSortieDtoIdExist(id);
    }

    @RequestMapping(value="sortie/modifiersor", method=RequestMethod.PUT)
    public Boolean modifierSortie(@RequestBody SortieDto sortieDto) {
        return sortieService.modifierSortie(sortieDto);
    }

    @RequestMapping(value="sortie/delete/{id}", method=RequestMethod.DELETE)
    public Boolean supprimerSortie(@PathVariable("id") Long id) {
        return sortieService.supprimerSortie(id);
    }
}
