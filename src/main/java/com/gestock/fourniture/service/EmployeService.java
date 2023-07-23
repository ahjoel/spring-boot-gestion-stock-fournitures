package com.gestock.fourniture.service;

import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.repository.EmployeRepository;
import com.gestock.fourniture.service.mapper.EmployeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeService {
    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EmployeMapper employeMapper;

    public List<EmployeDto> listEmployes(){
        return employeRepository.findAll().stream().map(employeMapper::toDto).collect(Collectors.toList());
    }

    public Long ajouterVoyage(EmployeDto employeDto){
        checkCodeEmpAlreadyUsed(employeDto);
        return employeRepository.save(employeMapper.toEntity(employeDto)).getId();
    }

    private void checkCodeEmpAlreadyUsed(EmployeDto employeDto) {
        if (employeRepository.existsByCodeEmpIgnoreCase(employeDto.getCodeEmp())){
            throw new RuntimeException("Code 5268 : Il existe déjà un employe avec ce code");
        }
    }
}
