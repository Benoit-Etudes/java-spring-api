package fr.benoitparmentier.msb.model.service;

import fr.benoitparmentier.msb.model.entity.Player;
import fr.benoitparmentier.msb.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> selectAll() {
        return (List<Player>) playerRepository.findAll();
    }

    public Player select(Integer id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        return optionalPlayer.orElse(null);
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public void delete(int id) {
        playerRepository.deleteById(id);
    }

    public void delete(Player player) {
        playerRepository.delete(player);
    }
}
