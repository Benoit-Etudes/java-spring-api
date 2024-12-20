package fr.benoitparmentier.msb.model.entity.dto;

import fr.benoitparmentier.msb.model.entity.Contest;
import fr.benoitparmentier.msb.model.entity.Player;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PlayerDTO {
    private Integer id;
    private String email;
    private String nickname;
    private List<String> wins = new ArrayList<String>();
    private List<String> contests = new ArrayList<String>();

    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.email = player.getEmail();
        this.nickname = player.getNickname();

        if (player.getWins() != null) {
            this.wins = player.getWins().stream().map(Contest::toString).collect(Collectors.toList());
        }

        if (player.getContests() != null) {
            this.contests = player.getContests()
                    .stream()
                    .map(Contest::toString)
                    .collect(Collectors.toList());
        }
    }

}
