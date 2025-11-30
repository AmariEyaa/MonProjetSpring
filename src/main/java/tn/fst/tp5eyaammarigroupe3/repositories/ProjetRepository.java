package tn.fst.tp5eyaammarigroupe3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.fst.tp5eyaammarigroupe3.entities.Projet;

@Repository
public interface ProjetRepository extends JpaRepository<Projet,Long> {
}
