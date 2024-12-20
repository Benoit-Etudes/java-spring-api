package fr.benoitparmentier.msb.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 50, nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "winner")
    private List<Contest> wins = new ArrayList<Contest>();

    @ManyToMany(mappedBy = "players")
    private List<Contest> contests = new ArrayList<Contest>();

    @Override
    public String toString() {
        return nickname;
    }
}
