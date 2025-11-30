package tn.fst.tp5eyaammarigroupe3.entities;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.*;

@Entity
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Enumerated(EnumType.STRING)
    private Domaine domaine;

    @ManyToMany
    private Set<Projet> projets = new HashSet<>();  // initialize here


    public Collection<Projet> getProjets() {
        return projets;
    }

    public void setProjets(Set<Projet> projets) {
        this.projets = projets;
    }
}
