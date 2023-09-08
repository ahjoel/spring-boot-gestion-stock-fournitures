package com.gestock.fourniture.service;

import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.model.entities.Categorie;
import com.gestock.fourniture.model.entities.Employe;
import com.gestock.fourniture.repository.EmployeRepository;
import com.gestock.fourniture.service.mapper.EmployeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EmployeMapper employeMapper;

    public List<EmployeDto> listEmployes(){
        return employeRepository.findAll().stream().sorted(Comparator.comparingLong(Employe::getId).reversed()).map(employeMapper::toDto).collect(Collectors.toList());
    }

    public Long ajouterEmploye(EmployeDto employeDto){
        checkCodeEmpAlreadyUsed(employeDto);
        return employeRepository.save(employeMapper.toEntity(employeDto)).getId();
    }

    private void checkCodeEmpAlreadyUsed(EmployeDto employeDto) {
        if (employeRepository.existsByCodeEmpIgnoreCase(employeDto.getCodeEmp())){
            throw new RuntimeException("Code 5268 : Il existe déjà un employe avec ce code");
        }
    }

    public EmployeDto getEmployeByCodeEmp(EmployeDto employeDto) {
        // Convertir EmployeDto en entité Employe
        Employe employe = employeMapper.toEntity(employeDto);

        // Recherchez le voyage par son nom en utilisant le Repository
        Employe employeFound = employeRepository.findEmployeByCodeEmp(employe.getCodeEmp())
                .orElseThrow(() -> new RuntimeException("Code 257 : l'employé que vous voulez modifier n'existe pas"));

        // Convertir l'entité Employe en EmployeDto et le renvoyer
        return employeMapper.toDto(employeFound);
    }

    public EmployeDto getEmployeById(EmployeDto employeDto) {
        // Convertir EmployeDto en entité Employe
        Employe employe = employeMapper.toEntity(employeDto);

        // Recherchez le voyage par son nom en utilisant le Repository
        Employe employeFound = employeRepository.findById(employe.getId())
                .orElseThrow(() -> new RuntimeException("Code 257 : l'id de l'employé que vous voulez n'existe pas"));

        // Convertir l'entité Employe en EmployeDto et le renvoyer
        return employeMapper.toDto(employeFound);
    }

    public boolean modifierEmploye(EmployeDto employeDto){
        // Convertir EmployeDto en entité Employe
        Employe employe = employeMapper.toEntity(employeDto);

        // Recherchez l'employe par son code en utilisant le Repository
        Employe employeFound = employeRepository.findById(employe.getId())
                .orElseThrow(() -> new RuntimeException("Code 257 : l'employé que vous voulez modifier n'existe pas"));

        //Rassemble tout ce qu'il a envoyé à modifier dans employeDto, le passe a l'entite employe
        employeMapper.copy(employeDto, employeFound);

        // Sauvegarder la modification en base de données
        employeRepository.save(employeFound);

        return true;
    }

    public boolean supprimerEmploye(Long id){
        Employe employe = employeRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Code 256 : l'employe que vous voulez supprimer n'existe pas"));

        employeRepository.deleteById(employe.getId());

        return true;
    }


}
