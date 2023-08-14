package com.gestock.fourniture.presentation;

import com.gestock.fourniture.model.dto.MouvementDto;
import com.gestock.fourniture.model.dto.SortieDto;
import com.gestock.fourniture.service.MouvementService;
import com.gestock.fourniture.service.SortieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class MouvementController {
    @Autowired
    MouvementService mouvementService;

    @RequestMapping(path="mouvements", method = RequestMethod.GET)
    public List<MouvementDto> listeMouvement() {
        return mouvementService.listMouvements();
    }

    @RequestMapping(value="mouvement/addmouv", method = RequestMethod.POST)
    public Long enregistrerMouvement(@RequestBody MouvementDto mouvementDto){
        return mouvementService.ajouterMouvement(mouvementDto);
    }

    private MouvementDto getMouvementDtoIdExist(Long id) {
        MouvementDto mouvementDto = MouvementDto.builder().id(id).build();
        MouvementDto mouvementDtoFound = mouvementService.getMouvementById(mouvementDto);

        if (mouvementDtoFound == null) {
            throw new RuntimeException("Code 257 : le code mouvement envoye n'existe pas");
        }
        return mouvementDtoFound;
    }

    @RequestMapping(value="mouvement/{id}", method = RequestMethod.GET)
    public MouvementDto afficherMouvement(@PathVariable("id") Long id) {
        return getMouvementDtoIdExist(id);
    }

    @RequestMapping(value="mouvement/modifiermouv", method=RequestMethod.PUT)
    public Boolean modifierMouvement(@RequestBody MouvementDto mouvementDto) {
        return mouvementService.modifierMouvement(mouvementDto);
    }

    @RequestMapping(value="mouvement/delete/{id}", method=RequestMethod.DELETE)
    public Boolean supprimerMouvement(@PathVariable("id") Long id) {
        return mouvementService.supprimerMouvement(id);
    }
}
