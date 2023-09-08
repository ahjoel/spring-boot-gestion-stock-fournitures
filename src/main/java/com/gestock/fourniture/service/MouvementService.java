package com.gestock.fourniture.service;

import com.gestock.fourniture.model.dto.MouvementDto;
import com.gestock.fourniture.model.dto.SortieDto;
import com.gestock.fourniture.model.entities.*;
import com.gestock.fourniture.repository.*;
import com.gestock.fourniture.service.mapper.MouvementMapper;
import com.gestock.fourniture.service.mapper.SortieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MouvementService {

    @Autowired
    MouvementRepository mouvementRepository;

    @Autowired
    FournitureRepository fournitureRepository;

    @Autowired
    LigneLivraisonRepository ligneLivraisonRepository;

    @Autowired
    SortieRepository sortieRepository;

    @Autowired
    MouvementMapper mouvementMapper;

    public List<MouvementDto> listMouvements(){
        return mouvementRepository.findAll().stream().sorted(Comparator.comparingLong(Mouvement::getId).reversed()).map(mouvementMapper::toDto).collect(Collectors.toList());
    }

    public Long ajouterMouvement(MouvementDto mouvementDto){
        //checkCodeMouvAlreadyUsed(mouvementDto);
        return mouvementRepository.save(mouvementMapper.toEntity(mouvementDto)).getId();
    }

    private void checkCodeMouvAlreadyUsed(MouvementDto mouvementDto) {
        if (mouvementRepository.existsByCodeMouvIgnoreCase(mouvementDto.getCodeMouv())){
            throw new RuntimeException("Code 5268 : Il existe déjà un mouvement avec ce code");
        }
    }

    public MouvementDto getMouvementByCodeMouv(MouvementDto mouvementDto) {
        Mouvement mouvement = mouvementMapper.toEntity(mouvementDto);

        Mouvement mouvementFound = mouvementRepository.findMouvementByCodeMouv(mouvement.getCodeMouv())
                .orElseThrow(() -> new RuntimeException("Code 257 : le code mouvement envoye n'existe pas"));

        return mouvementMapper.toDto(mouvementFound);
    }

    public MouvementDto getMouvementById(MouvementDto mouvementDto) {
        Mouvement mouvement = mouvementMapper.toEntity(mouvementDto);

        Mouvement mouvementFound = mouvementRepository.findById(mouvement.getId())
                .orElseThrow(() -> new RuntimeException("Code 257 : l'id du mouvement n'existe pas"));

        return mouvementMapper.toDto(mouvementFound);
    }

    public boolean modifierMouvement(MouvementDto mouvementDto){
        Mouvement mouvement = mouvementMapper.toEntity(mouvementDto);

        Mouvement mouvementFound = mouvementRepository.findById(mouvement.getId())
                .orElseThrow(() -> new RuntimeException("Code 257 : le mouvement que vous voulez modifier n'existe pas"));

        // Récupération de la fourniture associée à partir de la base de données
        Fourniture fourniture = fournitureRepository.findById(mouvementDto.getFourniture().getId())
                .orElseThrow(() -> new RuntimeException("Code 257 : Fourniture introuvable"));

        // Récupération de la ligne livraison associée à partir de la base de données
        LigneLivraison ligneLivraison = ligneLivraisonRepository.findById(mouvementDto.getLignelivraison().getId())
                .orElseThrow(() -> new RuntimeException("Code 257 : Livraison Fourniture introuvable"));

        // Récupération de la sortie associée à partir de la base de données
        Sortie sortie = sortieRepository.findById(mouvementDto.getSortie().getId())
                .orElseThrow(() -> new RuntimeException("Code 257 : Sortie Fourniture introuvable"));

        mouvementMapper.copy(mouvementDto, mouvementFound);

        mouvementRepository.save(mouvementFound);

        return true;
    }

    public boolean supprimerMouvement(Long id){
        Mouvement mouvement = mouvementRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Code 256 : le mouvement que vous voulez supprimer n'existe pas"));

        mouvementRepository.deleteById(mouvement.getId());

        return true;
    }

    public Long getTotalQuantiteByFournitureId(Long fournitureId) {
        return mouvementRepository.sumQuantiteByFournitureId(fournitureId);
    }

    public Long getTotalCommandeNonLiv() {
        return mouvementRepository.sumQuantiteTotalCommandeNonLiv();
    }

    public Long getTotalCommandeLiv() {
        return mouvementRepository.sumQuantiteTotalCommandeLiv();
    }

    public Long getTotalQuantiteFournitureEnregistrer() {
        return mouvementRepository.sumQuantiteTotalFournitureLiv();
    }

    public Long getTotalQuantiteSortie() {
        return mouvementRepository.sumQuantiteTotalSortie();
    }

    public List<Object[]> getSituationMensuelStock(LocalDate dateDebut, LocalDate dateFin) {
        return mouvementRepository.getSituationStockSurPeriode(dateDebut, dateFin);
    }
}
