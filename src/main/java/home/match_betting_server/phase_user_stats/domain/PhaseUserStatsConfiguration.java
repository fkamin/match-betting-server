package home.match_betting_server.phase_user_stats.domain;

import home.match_betting_server.phases.domain.PhaseRepository;
import home.match_betting_server.users.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PhaseUserStatsConfiguration {

    @Bean
    public PhaseUserStatsFacade phaseUserStatsFacade(
            UserRepository userRepository,
            PhaseRepository phaseRepository,
            PhaseUserStatsRepository phaseUserStatsRepository) {
        return new PhaseUserStatsFacade(userRepository, phaseRepository, phaseUserStatsRepository);
    }
}
