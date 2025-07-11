package home.match_betting_server.matches.domain;

import home.match_betting_server.phases.domain.Phase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByPhase(Phase phase);
}
