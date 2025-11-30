package tn.fst.tp5eyaammarigroupe3.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.fst.tp5eyaammarigroupe3.entities.Projet;
import tn.fst.tp5eyaammarigroupe3.entities.ProjetDetail;
import tn.fst.tp5eyaammarigroupe3.repositories.ProjetDetailRepository;
import tn.fst.tp5eyaammarigroupe3.repositories.ProjetRepository;
import tn.fst.tp5eyaammarigroupe3.services.interfaces.IProjetService;

@Service
@AllArgsConstructor
@Transactional
public class ProjetServiceImpl implements IProjetService
{
    private ProjetRepository projetRepository;
    private ProjetDetailRepository projetDetailRepository;


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



}
