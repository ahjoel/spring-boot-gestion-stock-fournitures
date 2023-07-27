package com.gestock.fourniture.presentation;

import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.model.entities.Employe;
import com.gestock.fourniture.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    @GetMapping("/lemployes")
    public String listeEmploye(Model model) {
        var employes = employeService.listEmployes();
        model.addAttribute("employes",employes);
        return "admin/listeEmploye";
    }

    @GetMapping("/formemploye")
    public String formulaireEmploye(Model model) {
        var employeDTO = EmployeDto.builder()
                .codeEmp("")
                .nomEmp("")
                .prenomEmp("")
                .serviceEmp("")
                .etat("ACTIVE")
                .build();

        model.addAttribute("employeDTO", employeDTO);

        return "admin/formEmploye";
    }

    @PostMapping("/enregistreremploye")
    public String enregistrerEmploye(@ModelAttribute EmployeDto employeDto, RedirectAttributes redirectAttributes){

        employeService.ajouterEmploye(employeDto);

        redirectAttributes.addFlashAttribute("message", "Employé crée avec succes !");

        return "redirect:lemployes";
    }

    @GetMapping("employe/{code}/edit")
    public String showEditForm(@PathVariable("code") String codeEmp, Model model) {

        EmployeDto employeDtoFound = getEmployeDtoCodeEmpExist(codeEmp);

        model.addAttribute("employeDto", employeDtoFound);

        return "admin/formEditEmploye";
    }

    private EmployeDto getEmployeDtoCodeEmpExist(String codeEmp) {
        EmployeDto employeDto = EmployeDto.builder().codeEmp(codeEmp).build();
        EmployeDto employeDtoFound = employeService.getEmployeByCodeEmp(employeDto);

        if (employeDtoFound == null) {
            throw new RuntimeException("Code 257 : le code employe que vous voulez modifier n'existe pas");
        }
        return employeDtoFound;
    }

    @PostMapping("employe/{code}/modify")
    public String processEditForm(@PathVariable("code") String codeEmp,
                                  @ModelAttribute("employeDto") EmployeDto employeDto,
                                  RedirectAttributes redirectAttributes) {
        EmployeDto employeDtoFound = getEmployeDtoCodeEmpExist(codeEmp);

        if (employeDtoFound == null) {
            redirectAttributes.addFlashAttribute("message", "Code 257 : le code employe que vous voulez modifier n'existe pas");
            return "admin/formEditEmploye";
        }

        try {
            employeService.modifierEmploye(employeDto);
            redirectAttributes.addFlashAttribute("message", "Modification de l'employé " + codeEmp + " effectuée");
            return "redirect:/lemployes";
        } catch (RuntimeException e) {
            String errorMessage = "Une erreur s'est produite lors de la modification de l'employe - " + e.getMessage();
            redirectAttributes.addFlashAttribute("message", errorMessage);
            return "admin/formEditEmploye";
        }
    }

    // Traitement de la suppression du voyage
    @PostMapping("employe/delete/{code}")
    public String deleteVoyage(@PathVariable("code") String codeEmp, RedirectAttributes redirectAttributes) {
        try {
            // Appelez la fonction de service pour supprimer le voyage
            employeService.supprimerEmploye(codeEmp);
            redirectAttributes.addFlashAttribute("message", "Suppression de l'employé " + codeEmp + " effectuée");
        } catch (RuntimeException e) {
            // Gérez les cas où le voyage n'existe pas ou la suppression échoue
            // Vous pouvez rediriger vers une page d'erreur ou afficher un message d'erreur
            // Ici, nous redirigeons simplement vers la liste des voyages en cas d'erreur
            return "redirect:/lemployes";
        }

        // Rediriger vers la liste des voyages après la suppression réussie
        return "redirect:/lemployes";
    }
}
