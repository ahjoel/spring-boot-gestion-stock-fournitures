package com.gestock.fourniture.presentation;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.model.dto.FournitureDto;
import com.gestock.fourniture.service.CategorieService;
import com.gestock.fourniture.service.FournitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        model.addAttribute("fournitures",fournitures);
        return "admin/fourniture/listeFourniture";
    }

    @GetMapping("/formfourniture")
    public String formulaireFourniture(Model model) {
        var fournitureDto = FournitureDto.builder()
                .codeFour("")
                .nomFour("")
                .mesureFour("")
                .qteMinFour("")
                .dateCreation(LocalDate.now())
                .dateModification(LocalDate.now())
                .etatFour("ACTIVE")
                .categorie(new CategorieDto())
                .build();

        model.addAttribute("fournitureDto", fournitureDto);
        model.addAttribute("categories", categorieService.listCategories());

        return "admin/fourniture/formFourniture";
    }

    @PostMapping("/enregistrerfourniture")
    public String enregistrerFourniture(@ModelAttribute FournitureDto fournitureDto, RedirectAttributes redirectAttributes){

        //System.out.println(fournitureDto);
        //fournitureService.ajouterFourniture(fournitureDto);

        //redirectAttributes.addFlashAttribute("message", "Fourniture crée avec succes !");

        try {
            fournitureService.ajouterFourniture(fournitureDto);
            redirectAttributes.addFlashAttribute("message", "Fourniture créée avec succès !");
            return "redirect:fournitures";
        } catch (RuntimeException e) {
            String errorMessage = "Code 5268 : Il existe déjà une fourniture avec ce code. ";
            redirectAttributes.addFlashAttribute("message", errorMessage);
            return "admin/fourniture/formFourniture";
        }

    }
}
