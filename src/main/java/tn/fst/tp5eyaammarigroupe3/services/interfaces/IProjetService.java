package tn.fst.tp5eyaammarigroupe3.services.interfaces;

import tn.fst.tp5eyaammarigroupe3.entities.Projet;

public interface IProjetService {
    //cas 1
    Projet addProjetAndProjetDetailAndAssign(Projet projet);
    // cas 2 : Affectation
    void assignProjetDetailToProjet(Long projetId, Long projetDetailId);

}
