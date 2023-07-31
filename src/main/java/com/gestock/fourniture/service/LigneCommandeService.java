package com.gestock.fourniture.service;

import com.gestock.fourniture.model.dto.CommandeDto;
import com.gestock.fourniture.model.dto.LigneCommandeDto;
import com.gestock.fourniture.repository.CommandeRepository;
import com.gestock.fourniture.repository.LigneCommandeRepository;
import com.gestock.fourniture.service.mapper.CommandeMapper;
import com.gestock.fourniture.service.mapper.LigneCommandeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LigneCommandeService {
    @Autowired
    LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    LigneCommandeMapper ligneCommandeMapper;

    public List<LigneCommandeDto> listLigneCommandes(){
        return ligneCommandeRepository.findAll().stream().map(ligneCommandeMapper::toDto).collect(Collectors.toList());
    }

    public Long ajouterLigneCommande(LigneCommandeDto ligneCommandeDto){
        //checkCodeComAlreadyUsed(commandeDto);
        return ligneCommandeRepository.save(ligneCommandeMapper.toEntity(ligneCommandeDto)).getId();
    }

}
