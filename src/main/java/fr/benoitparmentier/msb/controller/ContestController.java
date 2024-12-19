package fr.benoitparmentier.msb.controller;

import fr.benoitparmentier.msb.model.entity.Contest;
import fr.benoitparmentier.msb.model.entity.Game;
import fr.benoitparmentier.msb.model.entity.Player;
import fr.benoitparmentier.msb.model.service.ContestService;
import fr.benoitparmentier.msb.model.service.GameService;
import fr.benoitparmentier.msb.model.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ContestController {

    @Autowired
    private ContestService contestService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @GetMapping("/contests")
    public List<Contest> all() {
        return contestService.selectAll();
    }

    @GetMapping("/contest/{id}")
    public Contest get(
            @PathVariable int id
    ) {
        return contestService.select(id);
    }

    @PostMapping("/contest")
    public Contest create(
            @RequestParam("start_date") Date startDate,
            @RequestParam("game_id") Game game,
            @RequestParam("winner_id") Optional<Player> winner
    ) {
        Contest contest = new Contest();
        contest.setStartDate(startDate);
        contest.setGame(game);
        contest.setWinner(winner.orElse(null));

        return contestService.save(contest);
    }

    @PutMapping("/contest/{id}")
    public Contest create(@RequestBody Contest contest, @PathVariable int id) {
        contest.setId(id);
        return contestService.save(contest);
    }

    @DeleteMapping("/contest/{id}")
    public void deleteContest(@PathVariable int id) {
        contestService.delete(id);
    }
}
