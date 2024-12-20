package fr.benoitparmentier.msb.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


@CrossOrigin  // permet d'éviter les erreurs CORS
@RestController
public class ContestController {
    @Autowired ContestService contestService;

    @GetMapping("/contests")
    public List<ContestDTO> list() {
        return contestService.selectAll().stream().map(ContestDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/contest/{id}")
    public ContestDTO contest(@PathVariable int id) {
        return new ContestDTO(contestService.select(id));
    }

    // public ContestDTO contest(@RequestBody Contest contest) {
    //     return contestService.save(contest);
    // }

    /**
     * ? EXERCICE : Ecrire la méthode qui récupère les valeurs
     * ? d'un formulaire pour enregistrer une nouvelle partie (Contest)
     */
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
}
