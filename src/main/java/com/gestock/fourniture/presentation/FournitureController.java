package com.gestock.fourniture.presentation;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.model.dto.FournitureDto;
import com.gestock.fourniture.model.entities.Categorie;
import com.gestock.fourniture.service.CategorieService;
import com.gestock.fourniture.service.FournitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class FournitureController {
    @Autowired
    FournitureService fournitureService;

    @Autowired
    CategorieService categorieService;

    @GetMapping("/fournitures")
    public String listeFourniture(Model model) {
        var fournitures = fournitureService.listFournitures();
        System.out.println(fournitures);
        model.addAttribute("fournitures",fournitures);
        return "admin/fourniture/listeFourniture";
    }

    @GetMapping("/formfourniture")
    public String formulaireFourniture(Model model) {
        var categorieDTO = CategorieDto.builder()
                .codeCat("")
                .nomCat("")
                .descriptionCat("")
                .build();

        var fournitureDto = FournitureDto.builder()
                .codeFour("")
                .nomFour("")
                .mesureFour("")
                .qteMinFour("")
                .dateCreation(LocalDate.now())
                .dateModification(LocalDate.now())
                .etatFour("ACTIVE")
                .categorie(categorieDTO)
                .build();

        model.addAttribute("fournitureDto", fournitureDto);
        model.addAttribute("categories", categorieService.listCategories());

        return "admin/fourniture/formFourniture";
    }

    @PostMapping("/enregistrerfourniture")
    public String enregistrerFourniture(@ModelAttribute FournitureDto fournitureDto, RedirectAttributes redirectAttributes){
        try {
            fournitureService.ajouterFourniture(fournitureDto);
            redirectAttributes.addFlashAttribute("message", "Fourniture créée avec succès !");

        } catch (RuntimeException e) {
            String errorMessage = "Code 5268 : Il existe déjà une fourniture avec ce code. ";
            redirectAttributes.addFlashAttribute("message", errorMessage);
        }
        return "redirect:fournitures";
    }

    @GetMapping("fourniture/{code}/edit")
    public String showEditForm(@PathVariable("code") String codeFour, Model model) {

        FournitureDto fourDtoFound = getFournitureDtoCodeFourExist(codeFour);

        model.addAttribute("fournitureDTO", fourDtoFound);
        model.addAttribute("categories", categorieService.listCategories());

        return "admin/fourniture/formEditFourniture";
    }

    private FournitureDto getFournitureDtoCodeFourExist(String codeFour) {
        FournitureDto fournitureDto = FournitureDto.builder().codeFour(codeFour).build();
        FournitureDto fournitureDtoFound = fournitureService.getFournitutreByCodeFour(fournitureDto);

        if (fournitureDtoFound == null) {
            throw new RuntimeException("Code 257 : le code fourniture que vous voulez modifier n'existe pas");
        }
        return fournitureDtoFound;
    }

    @PostMapping("fourniture/{code}/modify")
    public String processEditForm(@PathVariable("code") String codeFour,
                                  @ModelAttribute("fournitureDTO") FournitureDto fournitureDto,
                                  RedirectAttributes redirectAttributes) {
        FournitureDto fournitureDtoFound = getFournitureDtoCodeFourExist(codeFour);

        if (fournitureDtoFound == null) {
            redirectAttributes.addFlashAttribute("message", "Code 257 : le code fourniture que vous voulez modifier n'existe pas");
            return "admin/fourniture/formEditFourniture";
        }

        try {
            fournitureService.modifierFourniture(fournitureDto);
            redirectAttributes.addFlashAttribute("message", "Modification de la fourniture " + codeFour + " effectuée");
        } catch (RuntimeException e) {
            String errorMessage = "Une erreur s'est produite lors de la modification de la fourniture - " + e.getMessage();
            redirectAttributes.addFlashAttribute("message", errorMessage);
        }
        return "redirect:/fournitures";
    }

    @PostMapping("fourniture/delete/{code}")
    public String deleteFourniture(@PathVariable("code") String codeFour, RedirectAttributes redirectAttributes) {
        try {
            fournitureService.supprimerFourniture(codeFour);
            redirectAttributes.addFlashAttribute("message", "Suppression de la fourniture " + codeFour + " effectuée");
        } catch (RuntimeException e) {
            // Gérez les cas où le voyage n'existe pas ou la suppression échoue
            // Vous pouvez rediriger vers une page d'erreur ou afficher un message d'erreur
            // Ici, nous redirigeons simplement vers la liste des voyages en cas d'erreur
            return "redirect:/fournitures";
        }

        // Rediriger vers la liste des voyages après la suppression réussie
        return "redirect:/fournitures";
    }
}
