package tn.fst.tp5eyaammarigroupe3.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Enumerated(EnumType.STRING)
    private Domaine domaine;

    @ManyToMany
    private List<Projet> projets;
}
