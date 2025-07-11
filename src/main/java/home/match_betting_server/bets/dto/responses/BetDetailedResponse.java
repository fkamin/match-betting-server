package home.match_betting_server.bets.dto.responses;

import home.match_betting_server.teams.domain.Team;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BetDetailedResponse {
    private Long id;
    private Long userId;
    private Long matchId;
    private Integer betLeftScore;
    private Integer betRightScore;
    private Team betWinnerTeam;
}
