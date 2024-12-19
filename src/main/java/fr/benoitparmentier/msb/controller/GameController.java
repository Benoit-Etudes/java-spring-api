package fr.benoitparmentier.msb.controller;

import fr.benoitparmentier.msb.model.entity.Game;
import fr.benoitparmentier.msb.model.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired GameService gameService;

    @GetMapping("/games")
    public List<Game> all() {
        return gameService.selectAll();
    }

    @GetMapping("/game/{id}")
    public Game get(
            @PathVariable int id
    ) {
        return gameService.select(id);
    }

    @PostMapping("/game")
    public Game create(@RequestBody Game game) {
        return gameService.save(game);
    }

    @PutMapping("/game/{id}")
    public Game create(@RequestBody Game game, @PathVariable int id) {
        game.setId(id);
        return gameService.save(game);
    }

    @DeleteMapping("/game/{id}")
    public void deleteGame(@PathVariable int id) {
        gameService.delete(id);
    }
}
