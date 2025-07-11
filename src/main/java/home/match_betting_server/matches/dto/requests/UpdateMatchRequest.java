package home.match_betting_server.matches.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UpdateMatchRequest {
    private Long teamLeftId;
    private Long teamRightId;
    private LocalDateTime matchDate;
}
