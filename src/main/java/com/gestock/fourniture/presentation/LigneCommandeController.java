package com.gestock.fourniture.presentation;

import com.gestock.fourniture.service.CommandeService;
import com.gestock.fourniture.service.FournitureService;
import com.gestock.fourniture.service.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LigneCommandeController {
    @Autowired
    LigneCommandeService ligneCommandeService;

    @Autowired
    CommandeService commandeService;

    @Autowired
    FournitureService fournitureService;

    @GetMapping("/lcommandes")
    public String listelcommande(Model model) {
        var lcommandes = ligneCommandeService.listLigneCommandes();
        model.addAttribute("lcommandes",lcommandes);
        return "admin/lcommande/listeLcommande";
    }

    @GetMapping("/formLcommande")
    public String formulairelcommande(Model model) {
        return "admin/lcommande/formLcommande";
    }
}
