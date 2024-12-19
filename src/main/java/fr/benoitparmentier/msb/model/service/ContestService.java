package fr.benoitparmentier.msb.model.service;

import fr.benoitparmentier.msb.model.entity.Contest;
import fr.benoitparmentier.msb.model.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContestService {
    @Autowired
    private ContestRepository contestRepository;

    public List<Contest> selectAll() {
        return (List<Contest>) contestRepository.findAll();
    }

    public Contest select(Integer id) {
        Optional<Contest> optionalContest = contestRepository.findById(id);
        return optionalContest.orElse(null);
    }

    public Contest save(Contest contest) {
        return contestRepository.save(contest);
    }

    public void delete(int id) {
        contestRepository.deleteById(id);
    }

    public void delete(Contest contest) {
        contestRepository.delete(contest);
    }
}
