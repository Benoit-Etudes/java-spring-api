package fr.benoitparmentier.msb.controller;

import fr.benoitparmentier.msb.model.entity.Player;
import fr.benoitparmentier.msb.model.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired PlayerService playerService;

    @GetMapping("/players")
    public List<Player> all() {
        return playerService.selectAll();
    }

    @GetMapping("/player/{id}")
    public Player get(
            @PathVariable int id
    ) {
        return playerService.select(id);
    }

    @PostMapping("/player")
    public Player create(@RequestBody Player player) {
        return playerService.save(player);
    }

    @PutMapping("/player/{id}")
    public Player create(@RequestBody Player player, @PathVariable int id) {
        player.setId(id);
        return playerService.save(player);
    }

    @DeleteMapping("/player/{id}")
    public void deletePlayer(@PathVariable int id) {
        playerService.delete(id);
    }
}
