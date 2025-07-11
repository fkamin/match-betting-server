package home.match_betting_server.bets.domain;

import home.match_betting_server.matches.domain.Match;
import home.match_betting_server.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {
    boolean existsByUserAndMatch(User user, Match match);
}
