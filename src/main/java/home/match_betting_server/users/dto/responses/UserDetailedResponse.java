package home.match_betting_server.users.dto.responses;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailedResponse {
    private Long id;
    private String name;
    private int points;
    private int betsWithMaxScore;
    private double percentageOfCorrectGuesses;
    private int rankingPosition;
}
