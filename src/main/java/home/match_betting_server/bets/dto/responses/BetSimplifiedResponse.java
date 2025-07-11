package home.match_betting_server.bets.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BetSimplifiedResponse {
    private Long id;
    private Integer betLeftScore;
    private Integer betRightScore;
}
