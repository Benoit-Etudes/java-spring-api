package fr.benoitparmentier.msb.model.entity.dto;

import fr.benoitparmentier.msb.model.entity.Contest;
import fr.benoitparmentier.msb.model.entity.Game;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameDTO {
    private Integer id;
    private String title;
    private Integer minPlayers;
    private Integer maxPlayers;
    private List<String> contests;

    public GameDTO(Game game) {
        this.id = game.getId();
        this.title = game.getTitle();
        this.minPlayers = game.getMinPlayers();
        this.maxPlayers = game.getMaxPlayers();

        List<String> contests = new ArrayList<String>();
        if (!game.getContests().isEmpty()) {
            for (Contest contest : game.getContests()) {
                contests.add(
                        String.format("Partie n°%d du %s", contest.getId(), contest.getStartDate().toString()
                    )
                );
            }
        }
        this.contests = contests;
    }
}
