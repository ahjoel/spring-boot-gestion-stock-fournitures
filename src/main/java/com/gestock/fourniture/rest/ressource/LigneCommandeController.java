package com.gestock.fourniture.rest.ressource;

import com.gestock.fourniture.model.dto.LigneCommandeDto;
import com.gestock.fourniture.service.CommandeService;
import com.gestock.fourniture.service.FournitureService;
import com.gestock.fourniture.service.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class LigneCommandeController {
    @Autowired
    LigneCommandeService ligneCommandeService;

    @RequestMapping(path="lignecommandes", method = RequestMethod.GET)
    public List<LigneCommandeDto> listeLigneCommande() {
        return ligneCommandeService.listLigneCommandes();
    }

     @RequestMapping(path="lignecommandes/nonlivree", method = RequestMethod.GET)
    public List<LigneCommandeDto> listeLigneCommandeLivree() {
        return ligneCommandeService.trierLigneCommandeParFournitureNonLivre();
    }

    @RequestMapping(value="lignecommande/addlcom", method = RequestMethod.POST)
    public Long enregistrerLigneCommande(@RequestBody LigneCommandeDto ligneCommandeDto){
        return ligneCommandeService.ajouterLigneCommande(ligneCommandeDto);
    }

    private LigneCommandeDto getLigneCommandeDtoIdExist(Long id) {
        LigneCommandeDto ligneCommandeDto = LigneCommandeDto.builder().id(id).build();
        LigneCommandeDto ligneCommandeDtoFound = ligneCommandeService.getLigneCommandeById(ligneCommandeDto);

        if (ligneCommandeDtoFound == null) {
            throw new RuntimeException("Code 257 : l'id de ligne commande envoye n'existe pas");
        }
        return ligneCommandeDtoFound;
    }

    @RequestMapping(value="lignecommande/{id}", method = RequestMethod.GET)
    public LigneCommandeDto afficherLigneCommande(@PathVariable("id") Long id) {
        return getLigneCommandeDtoIdExist(id);
    }

    @RequestMapping(value="lignecommande/modifierlcom", method=RequestMethod.PUT)
    public Boolean modifierLigneCommande(@RequestBody LigneCommandeDto ligneCommandeDto) {
        return ligneCommandeService.modifierLigneCommande(ligneCommandeDto);
    }

    @RequestMapping(value="lignecommande/delete/{id}", method=RequestMethod.DELETE)
    public Boolean supprimerLigneCommande(@PathVariable("id") Long id) {
        return ligneCommandeService.supprimerLigneCommande(id);
    }

    @RequestMapping(value="lignecommande/etatcmd/{id}", method=RequestMethod.PUT)
    public void updateLigneCommandeEtatToLivree(@PathVariable("id") Long id){
        ligneCommandeService.markAsLivree(id);
    }
}
