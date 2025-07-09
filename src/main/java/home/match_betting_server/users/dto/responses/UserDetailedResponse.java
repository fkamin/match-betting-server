package home.match_betting_server.users.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDetailedResponse {
    private Long id;
    private String name;
    private int points;
    private int betsWithMaxScore;
    private double percentageOfCorrectGuesses;
    private int rankingPosition;
}
