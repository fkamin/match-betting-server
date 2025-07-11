package home.match_betting_server.phases.dto.responses;

import home.match_betting_server.phases.domain.PhaseStatus;
import home.match_betting_server.phases.domain.PhaseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PhaseDetailedResponse {
    private Long id;
    private String name;
    private PhaseType phaseType;
    private PhaseStatus phaseStatus;
}
