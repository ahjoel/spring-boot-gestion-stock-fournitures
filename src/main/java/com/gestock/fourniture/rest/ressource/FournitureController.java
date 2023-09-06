package com.gestock.fourniture.rest.ressource;

import com.gestock.fourniture.model.dto.FournitureDto;
import com.gestock.fourniture.service.FournitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class FournitureController {
    @Autowired
    FournitureService fournitureService;

    @RequestMapping(path="fournitures", method = RequestMethod.GET)
    public List<FournitureDto> listeFourniture() {
        return fournitureService.listFournitures();
    }

    @RequestMapping(value="fourniture/addfour", method = RequestMethod.POST)
    public Long enregistrerFourniture(@RequestBody FournitureDto fournitureDto){
        return fournitureService.ajouterFourniture(fournitureDto);
    }

    private FournitureDto getFournitureDtoCodeFourExist(String codeFour) {
        FournitureDto fournitureDto = FournitureDto.builder().codeFour(codeFour).build();
        FournitureDto fournitureDtoFound = fournitureService.getFournitutreByCodeFour(fournitureDto);

        if (fournitureDtoFound == null) {
            throw new RuntimeException("Code 257 : le code fourniture envoye n'existe pas");
        }
        return fournitureDtoFound;
    }
    private FournitureDto getFournitureDtoIdExist(Long id) {
        FournitureDto fournitureDto = FournitureDto.builder().id(id).build();
        FournitureDto fournitureDtoFound = fournitureService.getFournitutreById(fournitureDto);

        if (fournitureDtoFound == null) {
            throw new RuntimeException("Code 257 : le code fourniture envoye n'existe pas");
        }
        return fournitureDtoFound;
    }

    @RequestMapping(value="fourniture/{id}", method = RequestMethod.GET)
    public FournitureDto afficherFourniture(@PathVariable("id") Long id) {
        return getFournitureDtoIdExist(id);
    }

    @RequestMapping(value="fourniture/modifierfour", method=RequestMethod.PUT)
    public Boolean modifierFourniture(@RequestBody FournitureDto fournitureDto) {
        return fournitureService.modifierFourniture(fournitureDto);
    }

    @RequestMapping(value="fourniture/delete/{id}", method=RequestMethod.DELETE)
    public Boolean supprimerFourniture(@PathVariable("id") Long id) {
        return fournitureService.supprimerFourniture(id);
    }
}
