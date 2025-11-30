package tn.fst.tp5eyaammarigroupe3.entities;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.Date;

@Entity

public class ProjetDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String technologie;
    private Long cout;
    private Date dateDebut;

    @Setter
    @OneToOne(mappedBy = "projetDetail")
    private Projet projet;


}
