package home.match_betting_server.phase_user_stats.domain;

import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhaseUserStatsRepository extends JpaRepository<PhaseUserStats, Long> {
    boolean existsByPhaseAndUser(Phase phase, User user);
}
