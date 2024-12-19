package fr.benoitparmentier.msb.model.repository;

import fr.benoitparmentier.msb.model.entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

}
