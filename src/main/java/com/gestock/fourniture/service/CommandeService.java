package com.gestock.fourniture.service;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.model.dto.CommandeDto;
import com.gestock.fourniture.model.entities.Categorie;
import com.gestock.fourniture.repository.CategorieRepository;
import com.gestock.fourniture.repository.CommandeRepository;
import com.gestock.fourniture.service.mapper.CategorieMapper;
import com.gestock.fourniture.service.mapper.CommandeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeService {
    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    CommandeMapper commandeMapper;

    public List<CommandeDto> listCommandes(){
        return commandeRepository.findAll().stream().map(commandeMapper::toDto).collect(Collectors.toList());
    }

    public Long ajouterCommande(CommandeDto commandeDto){
        checkCodeComAlreadyUsed(commandeDto);
        return commandeRepository.save(commandeMapper.toEntity(commandeDto)).getId();
    }

    private void checkCodeComAlreadyUsed(CommandeDto commandeDto) {
        if (commandeRepository.existsByCodeComIgnoreCase(commandeDto.getCodeCom())){
            throw new RuntimeException("Code 5268 : Il existe déjà une commande avec ce code");
        }
    }

}
