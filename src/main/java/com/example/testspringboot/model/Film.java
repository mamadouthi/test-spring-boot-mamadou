package com.example.testspringboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String titre;

    @Column
    String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "acteur_id", referencedColumnName = "id")
    List<Acteur> acteurs;
}
