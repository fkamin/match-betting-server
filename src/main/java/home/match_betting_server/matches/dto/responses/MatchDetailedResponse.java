package home.match_betting_server.matches.dto.responses;

import home.match_betting_server.matches.domain.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MatchDetailedResponse {
    private Long id;
    private Long phaseId;
    private String teamLeftName;
    private String teamRightName;
    private Integer teamLeftScore;
    private Integer teamRightScore;
    private String matchWinnerName;
    private LocalDateTime matchDate;
    private MatchStatus matchStatus;
}
