package fr.benoitparmentier.msb.controller;

import fr.benoitparmentier.msb.model.entity.Game;
import fr.benoitparmentier.msb.model.entity.dto.GameDTO;
import fr.benoitparmentier.msb.model.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
public class GameController {

    @Autowired GameService gameService;

    @GetMapping("/games")
    public List<GameDTO> all() {
        List<Game> games = gameService.selectAll();
        List<GameDTO> listDTO = new ArrayList<GameDTO>();
        for(Game game : games) {
            listDTO.add(new GameDTO(game));
        }
        return listDTO;
    }

    @GetMapping("/game/{id}")
    public GameDTO get(
            @PathVariable int id
    ) {
        return new GameDTO(gameService.select(id));
    }

    @PostMapping("/game")
    public GameDTO create(@RequestBody Game game) {
        return new GameDTO(gameService.save(game));
    }

    @PutMapping("/game/{id}")
    public GameDTO create(@RequestBody Game game, @PathVariable int id) {
        game.setId(id);
        return new GameDTO(gameService.save(game));
    }

    @DeleteMapping("/game/{id}")
    public void deleteGame(@PathVariable int id) {
        gameService.delete(id);
    }
}
