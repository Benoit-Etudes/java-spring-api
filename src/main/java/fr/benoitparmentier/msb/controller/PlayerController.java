package fr.benoitparmentier.msb.controller;

import fr.benoitparmentier.msb.model.entity.Player;
import fr.benoitparmentier.msb.model.entity.dto.PlayerDTO;
import fr.benoitparmentier.msb.model.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
public class PlayerController {

    @Autowired PlayerService playerService;

    @GetMapping("/players")
    public List<PlayerDTO> all() {
        List<Player> players = playerService.selectAll();
        List<PlayerDTO> listDTO = new ArrayList<PlayerDTO>();
        for(Player player : players) {
            listDTO.add(new PlayerDTO(player));
        }
        return listDTO;
    }

    @GetMapping("/player/{id}")
    public PlayerDTO get(
            @PathVariable int id
    ) {
        return new PlayerDTO(playerService.select(id));
    }

    @PostMapping("/player")
    public PlayerDTO create(@RequestBody Player player) {
        return new PlayerDTO(playerService.save(player));
    }

    @PutMapping("/player/{id}")
    public PlayerDTO create(@RequestBody Player player, @PathVariable int id) {
        player.setId(id);
        return new PlayerDTO(playerService.save(player));
    }

    @DeleteMapping("/player/{id}")
    public void deletePlayer(@PathVariable int id) {
        playerService.delete(id);
    }
}
