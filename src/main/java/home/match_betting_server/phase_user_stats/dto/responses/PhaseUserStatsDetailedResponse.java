package home.match_betting_server.phase_user_stats.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PhaseUserStatsDetailedResponse {
    private Long id;
    private Long userId;
    private Long phaseId;
    private int points;
    private int betsWithMaxScore;
    private int percentageOfCorrectGuesses;
    private int rankingPosition;
}
