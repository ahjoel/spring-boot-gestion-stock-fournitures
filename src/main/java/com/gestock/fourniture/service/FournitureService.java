package com.gestock.fourniture.service;

import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.model.dto.FournitureDto;
import com.gestock.fourniture.model.entities.Categorie;
import com.gestock.fourniture.model.entities.Employe;
import com.gestock.fourniture.model.entities.Fourniture;
import com.gestock.fourniture.repository.CategorieRepository;
import com.gestock.fourniture.repository.FournitureRepository;
import com.gestock.fourniture.service.mapper.FournitureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FournitureService {

    @Autowired
    FournitureRepository fournitureRepository;

    @Autowired
    CategorieRepository categorieRepository;

    @Autowired
    FournitureMapper fournitureMapper;

    public List<FournitureDto> listFournitures(){
        return fournitureRepository.findAll().stream().sorted(Comparator.comparingLong(Fourniture::getId).reversed()).map(fournitureMapper::toDto).collect(Collectors.toList());
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

    public FournitureDto getFournitutreByCodeFour(FournitureDto fournitureDto) {
        Fourniture fourniture = fournitureMapper.toEntity(fournitureDto);

        Fourniture fournitureFound = fournitureRepository.findFournitureByCodeFour(fourniture.getCodeFour())
                .orElseThrow(() -> new RuntimeException("Code 257 : le code fourniture envoye n'existe pas"));

        return fournitureMapper.toDto(fournitureFound);
    }

    public FournitureDto getFournitutreById(FournitureDto fournitureDto) {
        Fourniture fourniture = fournitureMapper.toEntity(fournitureDto);

        Fourniture fournitureFound = fournitureRepository.findById(fourniture.getId())
                .orElseThrow(() -> new RuntimeException("Code 257 : l'id de la fourniture n'existe pas"));

        return fournitureMapper.toDto(fournitureFound);
    }

    public boolean modifierFourniture(FournitureDto fournitureDto){
        Fourniture fourniture = fournitureMapper.toEntity(fournitureDto);

        Fourniture fournitureFound = fournitureRepository.findById(fourniture.getId())
                .orElseThrow(() -> new RuntimeException("Code 257 : la fourniture que vous voulez modifier n'existe pas"));

        // Récupération de la catégorie associée à partir de la base de données
        Categorie categorie = categorieRepository.findById(fournitureDto.getCategorie().getId())
                .orElseThrow(() -> new RuntimeException("Code 257 : Catégorie introuvable"));

        fournitureMapper.copy(fournitureDto, fournitureFound);

        fournitureRepository.save(fournitureFound);

        return true;
    }

    public boolean supprimerFourniture(Long id){
        Fourniture fourniture = fournitureRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Code 256 : la fourniture que vous voulez supprimer n'existe pas"));

        fournitureRepository.deleteById(fourniture.getId());

        return true;
    }
}
