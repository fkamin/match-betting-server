package home.match_betting_server.bets.domain;

import home.match_betting_server.matches.domain.MatchRepository;
import home.match_betting_server.phase_user_stats.domain.PhaseUserStatsRepository;
import home.match_betting_server.phases.domain.PhaseRepository;
import home.match_betting_server.teams.domain.TeamRepository;
import home.match_betting_server.users.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BetConfiguration {

    @Bean
    public BetFacade betFacade(
            BetRepository betRepository,
            PhaseRepository phaseRepository,
            MatchRepository matchRepository,
            UserRepository userRepository,
            PhaseUserStatsRepository phaseUserStatsRepository,
            TeamRepository teamRepository) {
        return new BetFacade(betRepository, phaseRepository, matchRepository, userRepository, phaseUserStatsRepository, teamRepository);
    }
}
