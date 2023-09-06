package com.gestock.fourniture.rest.ressource;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@CrossOrigin(origins = "*")
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

    private CategorieDto getCategorieDtoIdExist(Long id) {
        CategorieDto categorieDto = CategorieDto.builder().id(id).build();
        CategorieDto categorieDtoFound = categorieService.getCategorieById(categorieDto);

        if (categorieDtoFound == null) {
            throw new RuntimeException("Code 257 : l'id de la categorie que vous voulez modifier n'existe pas");
        }
        return categorieDtoFound;
    }

    @RequestMapping(value="categorie/{id}", method = RequestMethod.GET)
    public CategorieDto afficherCategorie(@PathVariable("id") Long id) {
        return getCategorieDtoIdExist(id);
    }

    @RequestMapping(value="categorie/modifiercat", method=RequestMethod.PUT)
    public boolean modifierCategorie(@RequestBody CategorieDto categorieDto) {
        return categorieService.modifierCategorie(categorieDto);
    }
    @RequestMapping(value="categorie/delete/{id}", method=RequestMethod.DELETE)
    public boolean deleteCategorie(@PathVariable("id") Long id) {
        return categorieService.supprimerCategorie(id);
    }
}
