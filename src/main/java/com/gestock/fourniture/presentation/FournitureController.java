package com.gestock.fourniture.presentation;

import com.gestock.fourniture.model.dto.FournitureDto;
import com.gestock.fourniture.service.CategorieService;
import com.gestock.fourniture.service.FournitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class FournitureController {
    @Autowired
    FournitureService fournitureService;

    @Autowired
    CategorieService categorieService;

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

    @RequestMapping(value="fourniture/{code}", method = RequestMethod.GET)
    public FournitureDto afficherFourniture(@PathVariable("code") String codeFour) {
        return getFournitureDtoCodeFourExist(codeFour);
    }

    @RequestMapping(value="fourniture/modifierfour", method=RequestMethod.PUT)
    public Boolean modifierFourniture(@RequestBody FournitureDto fournitureDto) {
        return fournitureService.modifierFourniture(fournitureDto);
    }

    @RequestMapping(value="fourniture/delete/{code}", method=RequestMethod.DELETE)
    public Boolean supprimerFourniture(@PathVariable("code") String codeFour) {
        return fournitureService.supprimerFourniture(codeFour);
    }
}
