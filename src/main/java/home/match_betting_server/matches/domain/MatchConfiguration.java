package home.match_betting_server.matches.domain;

import home.match_betting_server.phases.domain.PhaseRepository;
import home.match_betting_server.teams.domain.TeamRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchConfiguration {

    @Bean
    public MatchFacade matchFacade(MatchRepository matchRepository, PhaseRepository phaseRepository, TeamRepository teamRepository) {
        return new MatchFacade(matchRepository, phaseRepository, teamRepository);
    }
}
