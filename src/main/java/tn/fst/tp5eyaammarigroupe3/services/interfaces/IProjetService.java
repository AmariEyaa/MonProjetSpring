package tn.fst.tp5eyaammarigroupe3.services.interfaces;

import tn.fst.tp5eyaammarigroupe3.entities.Projet;

public interface IProjetService {
    //cas 1
    Projet addProjetAndProjetDetailAndAssign(Projet projet);
    // cas 2 : Affectation
    void assignProjetDetailToProjet(Long projetId, Long projetDetailId);
    //cas 3
    void assignProjetToEquipe(Long projetId, Long equipeId);
    // cas 4 : Ajouter un projet et affecter un ProjetDetail existant
    Projet addProjetAndAssignDetail(Long projetDetailId, Projet projet);
    // Cas 5 : Désaffectation ProjetDetail d’un Projet
    void removeProjetDetailFromProjet(Long projetId);
    // Cas 6 : Désaffectation Projet ↔ Equipe
    void unassignProjetFromEquipe(Long projetId, Long equipeId);

}
