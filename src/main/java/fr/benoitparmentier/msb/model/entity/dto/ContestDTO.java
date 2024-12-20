package fr.benoitparmentier.msb.model.entity.dto;

import fr.benoitparmentier.msb.model.entity.Contest;
import fr.benoitparmentier.msb.model.entity.Player;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ContestDTO {
    private Integer id;

    private String start_date;

    private String game;
    private Integer game_id;

    private String winner;
    private Integer winner_id;

    private List<String> players;
    private List<Integer> players_id;

    public ContestDTO(Contest contest) {
        this.id = contest.getId();
        this.start_date = contest.getStartDateFR();
        this.game = contest.getGame().getTitle();
        this.game_id = contest.getGame().getId();
        this.winner = contest.getWinner() != null ? contest.getWinner().getNickname() : "-";
        this.winner_id = contest.getWinner() != null ? contest.getWinner().getId() : null;

        this.players = contest.getPlayers()
                .stream()
                .map(Player::getNickname)
                .collect(Collectors.toList());

        this.players_id = contest.getPlayers()
                .stream()
                .map(Player::getId)
                .collect(Collectors.toList());
    }
}
