package home.match_betting_server.phase_user_stats.domain;

import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhaseUserStatsRepository extends JpaRepository<PhaseUserStats, Long> {
    boolean existsByUserAndPhase(User user, Phase phase);
    Optional<PhaseUserStats> findByUserAndPhase(User user, Phase phase);
    List<PhaseUserStats> findByPhase(Phase phase);
}
