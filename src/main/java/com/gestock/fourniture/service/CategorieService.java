package com.gestock.fourniture.service;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.model.dto.EmployeDto;
import com.gestock.fourniture.model.entities.Categorie;
import com.gestock.fourniture.model.entities.Employe;
import com.gestock.fourniture.repository.CategorieRepository;
import com.gestock.fourniture.service.mapper.CategorieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategorieService {
    @Autowired
    CategorieRepository categorieRepository;

    @Autowired
    CategorieMapper categorieMapper;

    public List<CategorieDto> listCategories(){
        return categorieRepository.findAll().stream().sorted(Comparator.comparingLong(Categorie::getId).reversed()).map(categorieMapper::toDto).collect(Collectors.toList());
    }

    public Long ajouterCategorie(CategorieDto categorieDto){
        checkCodeCatAlreadyUsed(categorieDto);
        return categorieRepository.save(categorieMapper.toEntity(categorieDto)).getId();
    }

    private void checkCodeCatAlreadyUsed(CategorieDto categorieDto) {
        if (categorieRepository.existsByCodeCatIgnoreCase(categorieDto.getCodeCat())){
            throw new RuntimeException("Code 5268 : Il existe déjà une categorie avec ce code");
        }
    }

    public CategorieDto getCategorieByCodeCat(CategorieDto categorieDto) {
        // Convertir CategorieDto en entité Categorie
        Categorie categorie = categorieMapper.toEntity(categorieDto);

        // Recherchez le categorie par son code en utilisant le Repository
        Categorie categorieFound = categorieRepository.findCategorieByCodeCat(categorie.getCodeCat())
                .orElseThrow(() -> new RuntimeException("Code 257 : la categorie que vous voulez modifier n'existe pas"));

        // Convertir l'entité Categorie en CategorieDto et le renvoyer
        return categorieMapper.toDto(categorieFound);
    }

    public CategorieDto getCategorieById(CategorieDto categorieDto) {
        // Convertir CategorieDto en entité Categorie
        Categorie categorie = categorieMapper.toEntity(categorieDto);

        // Recherchez le categorie par son code en utilisant le Repository
        Categorie categorieFound = categorieRepository.findById(categorie.getId())
                .orElseThrow(() -> new RuntimeException("Code 257 : l'id introuvable dans la base"));

        // Convertir l'entité Categorie en CategorieDto et le renvoyer
        return categorieMapper.toDto(categorieFound);
    }

    public boolean modifierCategorie(CategorieDto categorieDto){
        // Convertir CategorieDto en entité Categorie
        Categorie categorie = categorieMapper.toEntity(categorieDto);

        // Recherchez le categorie par son ID en utilisant le Repository
        Categorie categorieFound = categorieRepository.findCategorieById(categorie.getId())
                .orElseThrow(() -> new RuntimeException("Code 257 : l'id de la categorie que vous voulez modifier n'existe pas"));

        //Rassemble tout ce qu'il a envoyé à modifier dans voyageDto, le passe a l'entite voyage
        categorieMapper.copy(categorieDto, categorieFound);

        // Sauvegarder la modification en base de données
        categorieRepository.save(categorieFound);

        return true;
    }

    public boolean supprimerCategorie(Long id){
        Categorie categorie = categorieRepository.findCategorieById(id)
                .orElseThrow(()->new RuntimeException("Code 256 : la categorie que vous voulez supprimer n'existe pas"));

        categorieRepository.deleteById(categorie.getId());

        return true;
    }
}
