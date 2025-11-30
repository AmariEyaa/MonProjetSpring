package tn.fst.tp5eyaammarigroupe3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.fst.tp5eyaammarigroupe3.entities.ProjetDetail;

@Repository
public interface ProjetDetailRepository extends JpaRepository<ProjetDetail , Long> {
}
