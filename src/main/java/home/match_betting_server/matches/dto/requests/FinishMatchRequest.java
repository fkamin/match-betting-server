package home.match_betting_server.matches.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinishMatchRequest {
    private Integer teamLeftScore;
    private Integer teamRightScore;
    private Long winnerTeamId;

    public FinishMatchRequest(Integer teamLeftScore, Integer teamRightScore) {
        this.teamLeftScore = teamLeftScore;
        this.teamRightScore = teamRightScore;
    }
}
