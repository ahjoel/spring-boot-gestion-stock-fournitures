package com.gestock.fourniture.presentation;

import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    @GetMapping("/employes")
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
    public String enregistrerEmploye(@ModelAttribute EmployeDto employeDto){

        employeService.ajouterEmploye(employeDto);

        return "redirect:employes";
    }
}
