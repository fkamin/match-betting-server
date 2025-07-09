package home.match_betting_server.teams.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeamDetailedResponse {
    private Long id;
    private String name;
    private Long groupId;
}
