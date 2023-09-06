package com.gestock.fourniture.rest.ressource;

import com.gestock.fourniture.model.dto.LigneLivraisonDto;
import com.gestock.fourniture.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class LigneLivraisonController {
    @Autowired
    LigneLivraisonService ligneLivraisonService;

    @Autowired
    LivraisonService livraisonService;

    @Autowired
    LigneCommandeService ligneCommandeService;

    @Autowired
    FournitureService fournitureService;

    @RequestMapping(path="lignelivraisons", method = RequestMethod.GET)
    public List<LigneLivraisonDto> listeLigneLivraison() {
        return ligneLivraisonService.listLigneLivraisons();
    }

    @RequestMapping(value="lignelivraison/addlliv", method = RequestMethod.POST)
    public Long enregistrerLigneLivraison(@RequestBody LigneLivraisonDto ligneLivraisonDto){
        return ligneLivraisonService.ajouterLigneLivraison(ligneLivraisonDto);
    }

    private LigneLivraisonDto getLigneLivraisonDtoIdExist(Long id) {
        LigneLivraisonDto ligneLivraisonDto = LigneLivraisonDto.builder().id(id).build();
        LigneLivraisonDto ligneLivraisonFound = ligneLivraisonService.getLigneLivraisonById(ligneLivraisonDto);

        if (ligneLivraisonFound == null) {
            throw new RuntimeException("Code 257 : l'id de ligne livraison envoye n'existe pas");
        }
        return ligneLivraisonFound;
    }

    @RequestMapping(value="lignelivraison/{id}", method = RequestMethod.GET)
    public LigneLivraisonDto afficherLigneLivraison(@PathVariable("id") Long id) {
        return getLigneLivraisonDtoIdExist(id);
    }

    @RequestMapping(value="lignelivraison/modifierlliv", method=RequestMethod.PUT)
    public Boolean modifierLigneLivraison(@RequestBody LigneLivraisonDto ligneLivraisonDto) {
        return ligneLivraisonService.modifierLigneLivraison(ligneLivraisonDto);
    }

    @RequestMapping(value="lignelivraison/delete/{id}", method=RequestMethod.DELETE)
    public Boolean supprimerLigneLivraison(@PathVariable("id") Long id) {
        return ligneLivraisonService.supprimerLigneLivraison(id);
    }
}
