package home.match_betting_server.matches.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MatchSimplifiedResponse {
    private Long id;
    private Long phaseId;
    private String teamLeftName;
    private String teamRightName;
}
