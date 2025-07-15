package home.match_betting_server.bets.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BetSimplifiedResponse {
    private Long id;
    private Integer betLeftScore;
    private Integer betRightScore;
}
