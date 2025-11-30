package tn.fst.tp5eyaammarigroupe3.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sujet;

    @Getter
    @OneToOne
    private ProjetDetail projetDetail;

    @ManyToMany(mappedBy = "projets")
    private Set<Equipe> equipe;

}