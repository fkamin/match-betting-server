package home.match_betting_server.teams.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamConfiguration {
    @Bean
    public TeamFacade teamFacade(TeamRepository teamRepository, GroupRepository groupRepository) {
        return new TeamFacade(teamRepository, groupRepository);
    }
}
