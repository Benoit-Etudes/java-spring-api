package fr.benoitparmentier.msb.model.service;

import fr.benoitparmentier.msb.model.entity.Game;
import fr.benoitparmentier.msb.model.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> selectAll() {
        return (List<Game>) gameRepository.findAll();
    }

    public Game select(Integer id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        return optionalGame.orElse(null);
    }

    public Game save(Game game) {
        return gameRepository.save(game);
    }

    public void delete(int id) {
        gameRepository.deleteById(id);
    }

    public void delete(Game game) {
        gameRepository.delete(game);
    }
}
