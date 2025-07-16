package home.match_betting_server.bets.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateBetRequest {
    private Integer betLeftScore;
    private Integer betRightScore;
    private Long betWinnerTeamId;
}
