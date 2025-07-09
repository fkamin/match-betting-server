package home.match_betting_server.teams.dto.responses;

import home.match_betting_server.teams.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GroupDetailedResponse {
    private Long id;
    private String name;
    private List<Team> teams;
}
