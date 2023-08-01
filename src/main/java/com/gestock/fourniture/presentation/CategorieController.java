package com.gestock.fourniture.presentation;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CategorieController {

    @Autowired
    CategorieService categorieService;

    @RequestMapping(path="categories", method = RequestMethod.GET)
    public List<CategorieDto> listeCategorie() {
        return categorieService.listCategories();
    }

    @RequestMapping(value="categorie/addcat", method = RequestMethod.POST)
    public Long enregistrerCategorie(@RequestBody CategorieDto categorieDto){
        return categorieService.ajouterCategorie(categorieDto);
    }

    private CategorieDto getCategorieDtoCodeCatExist(String codeCat) {
        CategorieDto categorieDto = CategorieDto.builder().codeCat(codeCat).build();
        CategorieDto categorieDtoFound = categorieService.getCategorieByCodeCat(categorieDto);

        if (categorieDtoFound == null) {
            throw new RuntimeException("Code 257 : le code de la categorie que vous voulez modifier n'existe pas");
        }
        return categorieDtoFound;
    }

    @RequestMapping(value="categorie/{code}", method = RequestMethod.GET)
    public CategorieDto afficherCategorie(@PathVariable("code") String codeCat) {
        return getCategorieDtoCodeCatExist(codeCat);
    }

    @RequestMapping(value="categorie/modifiercat", method=RequestMethod.PUT)
    public boolean modifierCategorie(@RequestBody CategorieDto categorieDto) {
        return categorieService.modifierCategorie(categorieDto);
    }
    @RequestMapping(value="categorie/delete/{code}", method=RequestMethod.DELETE)
    public boolean deleteCategorie(@PathVariable("code") String codeCat) {
        return categorieService.supprimerCategorie(codeCat);
    }
}
