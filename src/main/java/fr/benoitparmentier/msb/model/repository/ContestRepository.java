package fr.benoitparmentier.msb.model.repository;

import fr.benoitparmentier.msb.model.entity.Contest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends CrudRepository<Contest, Integer> {

}
