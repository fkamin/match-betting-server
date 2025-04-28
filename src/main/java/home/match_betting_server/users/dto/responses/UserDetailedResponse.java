package home.match_betting_server.users.dto.responses;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailedResponse {
    public Long id;
    public String login;
    public String name;
    public Integer points;
    public Integer betsWithMaxScore;
    public Integer percentageOfCorrectGuesses;
    public Integer rankingPosition;
}
