package fr.benoitparmentier.msb.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 50, nullable = false)
    private String nickname;
}
