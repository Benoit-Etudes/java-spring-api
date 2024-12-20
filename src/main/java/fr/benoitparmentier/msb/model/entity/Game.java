package fr.benoitparmentier.msb.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "game")
    private List<Contest> contests = new ArrayList<Contest>();

    @Override
    public String toString() {
        return title;
    }
}
