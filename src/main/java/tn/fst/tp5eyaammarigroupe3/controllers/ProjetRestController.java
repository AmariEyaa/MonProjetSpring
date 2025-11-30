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
    //cas 3
    @PutMapping("/affecter-projet-equipe/{projet-id}/{equipe-id}")
    public void affecterProjetAEquipe(@PathVariable("projet-id") Long projetId,
                                      @PathVariable("equipe-id") Long equipeId){
        projetService.assignProjetToEquipe(projetId, equipeId);
    }

    // Cas 4 : Ajouter projet et affecter ProjetDetail existant
    @PostMapping("/ajouter-et-affecter-projet-detail/{projet-detail-id}")
    public Projet ajouterEtAffecterProjetDetail(@PathVariable("projet-detail-id") Long projetDetailId,
                                                @RequestBody Projet projet) {
        return projetService.addProjetAndAssignDetail(projetDetailId, projet);
    }

    //cas 5
    @DeleteMapping("/desaffecter-projet-detail/{projet-id}")
    public void desaffecterProjetDetail(@PathVariable("projet-id") Long projetId) {
        projetService.removeProjetDetailFromProjet(projetId);
    }

    // Cas 6 : Désaffectation Projet ↔ Equipe
    @DeleteMapping("/desaffecter-projet-de-equipe/{projet-id}/{equipe-id}")
    public void desaffecterProjetDeEquipe(@PathVariable("projet-id") Long projetId,
                                          @PathVariable("equipe-id") Long equipeId){
        projetService.unassignProjetFromEquipe(projetId, equipeId);
    }






}
