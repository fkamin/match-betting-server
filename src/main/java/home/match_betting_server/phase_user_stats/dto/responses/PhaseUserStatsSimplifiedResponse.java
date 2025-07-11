package home.match_betting_server.phase_user_stats.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PhaseUserStatsSimplifiedResponse {
    private Long id;
    private Long userId;
    private Long phaseId;
}
