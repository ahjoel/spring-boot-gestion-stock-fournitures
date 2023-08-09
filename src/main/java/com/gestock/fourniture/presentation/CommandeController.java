package com.gestock.fourniture.presentation;

import com.gestock.fourniture.model.dto.CategorieDto;
import com.gestock.fourniture.model.dto.CommandeDto;
import com.gestock.fourniture.model.dto.LigneCommandeDto;
import com.gestock.fourniture.service.CommandeService;
import com.gestock.fourniture.service.FournitureService;
import com.gestock.fourniture.service.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CommandeController {
    @Autowired
    CommandeService commandeService;

    @RequestMapping(path="commandes", method = RequestMethod.GET)
    public List<CommandeDto> listeCommande() {
        return commandeService.listCommandes();
    }

    @RequestMapping(value="commande/addcom", method = RequestMethod.POST)
    public CommandeDto enregistrerCommande(@RequestBody CommandeDto commandeDto){
        return commandeService.ajouterCommande(commandeDto);
    }

    private CommandeDto getCommandeDtoIdExist(Long id) {
        CommandeDto commandeDto = CommandeDto.builder().id(id).build();
        CommandeDto commandeDtoFound = commandeService.getCommandeById(commandeDto);

        if (commandeDtoFound == null) {
            throw new RuntimeException("Code 257 : l'id de la commande que vous voulez modifier n'existe pas");
        }
        return commandeDtoFound;
    }

    @RequestMapping(value="commande/{id}", method = RequestMethod.GET)
    public CommandeDto afficherCommande(@PathVariable("id") Long id) {
        return getCommandeDtoIdExist(id);
    }

    @RequestMapping(value="commande/delete/{id}", method=RequestMethod.DELETE)
    public Boolean supprimerCommande(@PathVariable("id") Long id) {
        return commandeService.supprimerCommande(id);
    }

}
