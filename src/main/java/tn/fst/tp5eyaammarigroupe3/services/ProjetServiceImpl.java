package tn.fst.tp5eyaammarigroupe3.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import tn.fst.tp5eyaammarigroupe3.entities.Equipe;
import tn.fst.tp5eyaammarigroupe3.entities.Projet;
import tn.fst.tp5eyaammarigroupe3.entities.ProjetDetail;
import tn.fst.tp5eyaammarigroupe3.repositories.EquipeRepository;
import tn.fst.tp5eyaammarigroupe3.repositories.ProjetDetailRepository;
import tn.fst.tp5eyaammarigroupe3.repositories.ProjetRepository;
import tn.fst.tp5eyaammarigroupe3.services.interfaces.IProjetService;

import java.util.ArrayList;
import java.util.HashSet;

@Service
@AllArgsConstructor
@Transactional
public class ProjetServiceImpl implements IProjetService {
    private ProjetRepository projetRepository;
    private ProjetDetailRepository projetDetailRepository;
    private final EquipeRepository equipeRepository;


    @Override
    public Projet addProjetAndProjetDetailAndAssign(Projet projet) {
        ProjetDetail detail = projet.getProjetDetail();
        detail.setProjet(projet);
        projetDetailRepository.save(detail);
        return projetRepository.save(projet);
    }

    @Override
    public void assignProjetDetailToProjet(Long projetId, Long projetDetailId) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec id : " + projetId));

        ProjetDetail projetDetail = projetDetailRepository.findById(projetDetailId)
                .orElseThrow(() -> new RuntimeException("ProjetDetail non trouvé avec id : " + projetDetailId));

        // Affecter le ProjetDetail au Projet
        projetDetail.setProjet(projet);
        projetDetailRepository.save(projetDetail);
    }
    @Override
    public void assignProjetToEquipe(Long projetId, Long equipeId) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));

        Equipe equipe = equipeRepository.findById(equipeId)
                .orElseThrow(() -> new RuntimeException("Equipe non trouvée"));

        // Add equipe to projet
        projet.addEquipe(equipe);

        // Initialize projets set if null, then add projet
        if (equipe.getProjets() == null) {
            equipe.setProjets(new HashSet<>());
        }
        equipe.getProjets().add(projet);

        // Save both to persist relationship
        projetRepository.save(projet);
        equipeRepository.save(equipe);
    }

    @Override
    public Projet addProjetAndAssignDetail(Long projetDetailId, Projet projet) {
        // Récupérer le ProjetDetail existant
        ProjetDetail projetDetail = projetDetailRepository.findById(projetDetailId)
                .orElseThrow(() -> new RuntimeException("ProjetDetail non trouvé avec id : " + projetDetailId));

        // Lier ProjetDetail au projet
        projet.setProjetDetail(projetDetail);  // si tu as un setter ou via reflection
        projetDetail.setProjet(projet);

        // Sauvegarder les deux
        projetDetailRepository.save(projetDetail);
        return projetRepository.save(projet);
    }

    @Override
    public void removeProjetDetailFromProjet(Long projetId) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));

        ProjetDetail detail = projet.getProjetDetail();
        if (detail != null) {
            detail.setProjet(null);   // désaffecte le ProjetDetail
            projetDetailRepository.save(detail);

            projet.setProjetDetail(null); // si tu veux que Projet ne pointe plus sur ce ProjetDetail
            projetRepository.save(projet);
        }
    }
    @Override
    public void unassignProjetFromEquipe(Long projetId, Long equipeId) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));
        Equipe equipe = equipeRepository.findById(equipeId)
                .orElseThrow(() -> new RuntimeException("Equipe non trouvée"));

        // Retirer l'association dans les deux sens
        projet.getEquipes().remove(equipe);
        equipe.getProjets().remove(projet);

        // Sauvegarder les deux entités
        projetRepository.save(projet);
        equipeRepository.save(equipe);
    }





}








