package fr.benoitparmentier.msb.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.benoitparmentier.msb.model.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.benoitparmentier.msb.model.entity.Contest;
import fr.benoitparmentier.msb.model.entity.Game;
import fr.benoitparmentier.msb.model.entity.Player;
import fr.benoitparmentier.msb.model.entity.dto.ContestDTO;
import fr.benoitparmentier.msb.model.service.ContestService;

@CrossOrigin
@RestController
public class ContestController {
    @Autowired
    private ContestService contestService;

    @Autowired
    private PlayerService playerService;

    @GetMapping("/contests")
    public List<ContestDTO> list() {
        return contestService.selectAll().stream().map(ContestDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/contest/{id}")
    public ContestDTO contest(@PathVariable int id) {
        return new ContestDTO(contestService.select(id));
    }

    @PostMapping("/contest")
    public ContestDTO add(@RequestParam("start_date") Date startDate,
                          @RequestParam("game_id") Game game,
                          @RequestParam("winner_id") Optional<Player> winner) {
        Contest contest = new Contest();
        contest.setGame(game);
        contest.setStartDate(startDate);
        contest.setWinner( winner.orElse(null));
        return new ContestDTO(contestService.save(contest));
    }


    @DeleteMapping("/contest/{id}")
    public Boolean delete(@PathVariable int id) {
        Contest contest = contestService.select(id);
        if( contest != null ) {
            contestService.delete(contest);
            return true;
        }
        return false;
    }

    @PutMapping("/contest/{id}")
    public ContestDTO update(@PathVariable int id,
                             @RequestBody Contest contest) {
        if(id == contest.getId()) {
            return new ContestDTO(contestService.save(contest));
        }
        return null;
    }

    // --- EXERCICES ---
    //? 1. Possibilité d'ajouter un joueur à une partie existante
    //? 2. Possibilité d'ajouter plusieurs joueurs d'un coup à une partie existante
    //? 3. Possibilité de supprimer un joueur d'une partie
    //? 4. Possibilité de choisir le vainqueur d'une partie parmi les participants. Une fois que le vainqueur a été choisi, il ne peut plus être modifié.
    //* 6. [Bonus] les modifications précédentes ne peuvent se faire que si la partie n'a pas encore eu lieu.
    //* Après, aucune modification n'est possible sauf pour désigner le vainqueur qui ne peut être désigné
    //* que si la partie a déjà eu lieu (le jour même ou après).

    @PostMapping("/contest/{id}/players")
    public ContestDTO addPlayersToContest(@PathVariable int id, @RequestBody List<Integer> playersIds) {
        Contest contest = contestService.select(id);

        if (contest.getStartDate().before(new Date(System.currentTimeMillis()))) {
            return null;
        }

        List<Player> playerList = contest.getPlayers();
        List<Player> playersToAdd = playersIds.stream()
                .map(playerId -> playerService.select(playerId))
                .filter(player -> player != null && !playerList.contains(player))
                .toList();
        playerList.addAll(playersToAdd);
        contest.setPlayers(playerList);

        return new ContestDTO(contestService.save(contest));
    }

    @PostMapping("/contest/{contestId}/player/{player}")
    public ContestDTO addPlayerToContest(@PathVariable int contestId, @PathVariable Player player) {
        Contest contest = contestService.select(contestId);

        if (player != null && !contest.getPlayers().contains(player) && !contest.getStartDate().before(new Date(System.currentTimeMillis()))) {
            List<Player> playerList = contest.getPlayers();
            playerList.add(player);
            contest.setPlayers(playerList);

            return new ContestDTO(contestService.save(contest));
        }
        return null;
    }

    @PutMapping("/contest/{contestId}/winner/{winnerId}")
    public ContestDTO defineWinner(@PathVariable int contestId, @PathVariable int winnerId) {
        Contest contest = contestService.select(contestId);
        if(contest.getWinner() != null || new Date(System.currentTimeMillis()).before(contest.getStartDate())) return null;

        Player winner = playerService.select(winnerId);
        if (!contest.getPlayers().contains(winner)) return null;

        contest.setWinner(winner);

        return new ContestDTO(contestService.save(contest));
    }

    @DeleteMapping("/contest/{contestId}/player/{playerId}")
    public boolean deletePlayerToContest(@PathVariable int contestId, @PathVariable int playerId) {
        Contest contest = contestService.select(contestId);

        if (contest.getStartDate().before(new Date(System.currentTimeMillis()))) {
            return false;
        }

        List<Player> contestPlayers = contest.getPlayers();
        contest.setPlayers(
                contestPlayers
                        .stream()
                        .filter(player -> player.getId() != playerId)
                        .collect(Collectors.toList())
        );
        contestService.save(contest);
        return true;
    }
}
