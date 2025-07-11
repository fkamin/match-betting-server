package home.match_betting_server.matches.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FinishMatchRequest {
    private Integer teamLeftScore;
    private Integer teamRightScore;
}
