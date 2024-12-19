package fr.benoitparmentier.msb.model.repository;

import fr.benoitparmentier.msb.model.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

}
