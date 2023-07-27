package com.gestock.fourniture.presentation;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class CategorieController {

    @Autowired
    CategorieService categorieService;

    @GetMapping("/categories")
    public String listeCategorie(Model model) {
        var categories = categorieService.listCategories();
        model.addAttribute("categories",categories);
        return "admin/categorie/listeCategorie";
    }

    @GetMapping("/formcategorie")
    public String formulaireCategorie(Model model) {
        var categorieDTO = CategorieDto.builder()
                .codeCat("")
                .nomCat("")
                .descriptionCat("")
                .build();

        model.addAttribute("categorieDTO", categorieDTO);

        return "admin/categorie/formCategorie";
    }

    @PostMapping("/enregistrercategorie")
    public String enregistrerCategorie(@ModelAttribute CategorieDto categorieDto, RedirectAttributes redirectAttributes){

        categorieService.ajouterCategorie(categorieDto);

        redirectAttributes.addFlashAttribute("message", "Catégorie crée avec succes !");

        return "redirect:categories";
    }

    @GetMapping("categorie/{code}/edit")
    public String showEditForm(@PathVariable("code") String codeCat, Model model) {

        CategorieDto categorieDtoFound = getCategorieDtoCodeCatExist(codeCat);

        model.addAttribute("categorieDto", categorieDtoFound);

        return "admin/categorie/formEditCategorie";
    }

    private CategorieDto getCategorieDtoCodeCatExist(String codeCat) {
        CategorieDto categorieDto = CategorieDto.builder().codeCat(codeCat).build();
        CategorieDto categorieDtoFound = categorieService.getCategorieByCodeCat(categorieDto);

        if (categorieDtoFound == null) {
            throw new RuntimeException("Code 257 : le code de la categorie que vous voulez modifier n'existe pas");
        }
        return categorieDtoFound;
    }

    @PostMapping("categorie/{code}/modify")
    public String processEditForm(@PathVariable("code") String codeCat,
                                  @ModelAttribute("categorieDto") CategorieDto categorieDto,
                                  RedirectAttributes redirectAttributes) {
        CategorieDto categorieDtoFound = getCategorieDtoCodeCatExist(codeCat);

        if (categorieDtoFound == null) {
            redirectAttributes.addFlashAttribute("message", "Code 257 : le code categorie que vous voulez modifier n'existe pas");
            return "admin/categorie/formEditCategorie";
        }

        try {
            categorieService.modifierCategorie(categorieDto);
            redirectAttributes.addFlashAttribute("message", "Modification de la categorie " + codeCat + " effectuée");
            return "redirect:/categories";
        } catch (RuntimeException e) {
            String errorMessage = "Une erreur s'est produite lors de la modification de la categorie - " + e.getMessage();
            redirectAttributes.addFlashAttribute("message", errorMessage);
            return "admin/categorie/formEditCategorie";
        }
    }

    // Traitement de la suppression de l'employe
    @PostMapping("categorie/delete/{code}")
    public String deleteCategorie(@PathVariable("code") String codeCat, RedirectAttributes redirectAttributes) {
        try {
            categorieService.supprimerCategorie(codeCat);
            redirectAttributes.addFlashAttribute("message", "Suppression de la categorie " + codeCat + " effectuée");
        } catch (RuntimeException e) {
            // Gérez les cas où le voyage n'existe pas ou la suppression échoue
            // Vous pouvez rediriger vers une page d'erreur ou afficher un message d'erreur
            // Ici, nous redirigeons simplement vers la liste des voyages en cas d'erreur
            return "redirect:/categories";
        }

        // Rediriger vers la liste des voyages après la suppression réussie
        return "redirect:/categories";
    }
}
