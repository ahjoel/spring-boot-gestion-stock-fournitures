package com.gestock.fourniture.rest.ressource;

import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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

    private EmployeDto getEmployeDtoIdExist(Long id) {
        EmployeDto employeDto = EmployeDto.builder().id(id).build();
        EmployeDto employeDtoFound = employeService.getEmployeById(employeDto);

        if (employeDtoFound == null) {
            throw new RuntimeException("Code 257 : l'id de l'employe que vous voulez n'existe pas");
        }
        return employeDtoFound;
    }

    @RequestMapping(value="employe/{id}", method = RequestMethod.GET)
    public EmployeDto afficherEmploye(@PathVariable("id") Long id) {
        return getEmployeDtoIdExist(id);
    }

    @RequestMapping(value="employe/modifieremp", method=RequestMethod.PUT)
    public boolean modifierEmploye(@RequestBody EmployeDto employeDto) {
        return employeService.modifierEmploye(employeDto);
    }

    @RequestMapping(value="employe/delete/{id}", method=RequestMethod.DELETE)
    public boolean deleteEmploye(@PathVariable("id") Long id) {
        return employeService.supprimerEmploye(id);
    }
}
