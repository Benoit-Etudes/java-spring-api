package fr.benoitparmentier.msb.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String title;

    private Integer minPlayers;
    private Integer maxPlayers;

}
