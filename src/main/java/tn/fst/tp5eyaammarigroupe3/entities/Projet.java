package tn.fst.tp5eyaammarigroupe3.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
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

    @ManyToMany
    private Set<Equipe> equipes = new HashSet<>();  // initialize here


    public void addEquipe(Equipe equipe) {
        equipes.add(equipe);
    }

    public void setProjetDetail(ProjetDetail projetDetail) {
        this.projetDetail = projetDetail;
    }

    public Set<Equipe> getEquipes() {
        return equipes;
    }
}