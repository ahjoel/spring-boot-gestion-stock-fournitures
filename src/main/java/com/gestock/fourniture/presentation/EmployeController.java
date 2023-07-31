package com.gestock.fourniture.presentation;

import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.model.entities.Employe;
import com.gestock.fourniture.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@RestController
@RequestMapping("/api")
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    @RequestMapping(path="employes", method = RequestMethod.GET)
    public List<EmployeDto> listeEmploye() {
        return employeService.listEmployes();
    }

    @RequestMapping(value="employe/addemp", method = RequestMethod.POST)
    public Long enregistrerEmploye(@RequestBody EmployeDto employeDto){
        return employeService.ajouterEmploye(employeDto);
    }

    private EmployeDto getEmployeDtoCodeEmpExist(String codeEmp) {
        EmployeDto employeDto = EmployeDto.builder().codeEmp(codeEmp).build();
        EmployeDto employeDtoFound = employeService.getEmployeByCodeEmp(employeDto);

        if (employeDtoFound == null) {
            throw new RuntimeException("Code 257 : le code employe que vous voulez modifier n'existe pas");
        }
        return employeDtoFound;
    }

    @RequestMapping(value="employe/{code}", method = RequestMethod.GET)
    public EmployeDto afficherEmploye(@PathVariable("code") String codeEmp) {
        return getEmployeDtoCodeEmpExist(codeEmp);
    }

    @RequestMapping(value="/modifieremp", method=RequestMethod.PUT)
    public boolean modifierEmploye(@RequestBody EmployeDto employeDto) {
        return employeService.modifierEmploye(employeDto);
    }

    //@RequestMapping(value="/delprod/{id}", method=RequestMethod.DELETE)
    //public void deleteProduit(@PathVariable("id") Long id) {
    //    produitService.deleteProduitById(id);
    //}


    // Traitement de la suppression de l'employe
    @RequestMapping(value="/suppemp/{id}", method=RequestMethod.DELETE)
    @PostMapping("employe/delete/{code}")
    public String deleteEmploye(@PathVariable("code") String codeEmp, RedirectAttributes redirectAttributes) {
        try {
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
