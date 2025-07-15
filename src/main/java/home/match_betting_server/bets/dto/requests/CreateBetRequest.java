package home.match_betting_server.bets.dto.requests;

import home.match_betting_server.teams.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateBetRequest {
    private Long matchId;
    private Integer betLeftScore;
    private Integer betRightScore;
    private Team betWinnerTeam;
}
