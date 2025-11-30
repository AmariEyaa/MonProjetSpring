package tn.fst.tp5eyaammarigroupe3.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.fst.tp5eyaammarigroupe3.entities.Projet;
import tn.fst.tp5eyaammarigroupe3.services.interfaces.IProjetService;

@RestController
@AllArgsConstructor
@RequestMapping("/projet")


public class ProjetRestController {


    private final IProjetService projetService;

    // Cas 1 : Ajouter un Projet et son ProjetDetail
    @PostMapping("/ajouter-projet-et-projet-detail")
    public Projet ajouterProjetEtDetail(@RequestBody Projet p) {
        // Appel du service qui crée et lie Projet + ProjetDetail
        return projetService.addProjetAndProjetDetailAndAssign(p);
}
        //cas 2
    @PutMapping("/affecter-projet-a-projet-details/{projet-id}/{projet-details-id}")
    public void affecterProjetAProjetDetail(@PathVariable("projet-id") Long projetId,
                                            @PathVariable("projet-details-id") Long projetDetailsId){
        projetService.assignProjetDetailToProjet(projetId,projetDetailsId);
    }


}
