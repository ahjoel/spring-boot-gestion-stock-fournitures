package com.gestock.fourniture.service;

import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.model.dto.FournitureDto;
import com.gestock.fourniture.model.entities.Fourniture;
import com.gestock.fourniture.repository.FournitureRepository;
import com.gestock.fourniture.service.mapper.FournitureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FournitureService {

    @Autowired
    FournitureRepository fournitureRepository;

    @Autowired
    FournitureMapper fournitureMapper;

    public List<FournitureDto> listFournitures(){
        return fournitureRepository.findAll().stream().map(fournitureMapper::toDto).collect(Collectors.toList());
    }

    public Long ajouterFourniture(FournitureDto fournitureDto){
        checkCodeFourAlreadyUsed(fournitureDto);
        return fournitureRepository.save(fournitureMapper.toEntity(fournitureDto)).getId();
    }

    private void checkCodeFourAlreadyUsed(FournitureDto fournitureDto) {
        if (fournitureRepository.existsByCodeFourIgnoreCase(fournitureDto.getCodeFour())){
            throw new RuntimeException("Code 5268 : Il existe déjà une fourniture avec ce code");
        }
    }
}
