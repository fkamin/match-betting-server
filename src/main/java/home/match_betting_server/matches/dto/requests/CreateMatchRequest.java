package home.match_betting_server.matches.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateMatchRequest {
    private Long teamLeftId;
    private Long teamRightId;
    private LocalDateTime matchDate;
}
