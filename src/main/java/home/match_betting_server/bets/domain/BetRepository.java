package home.match_betting_server.bets.domain;

import home.match_betting_server.matches.domain.Match;
import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {
    boolean existsByUserAndMatch(User user, Match match);
    List<Bet> findAllByUserAndMatchPhase(User user, Phase phase);
}
