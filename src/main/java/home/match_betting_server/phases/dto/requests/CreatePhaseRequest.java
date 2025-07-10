package home.match_betting_server.phases.dto.requests;

import home.match_betting_server.phases.domain.PhaseStatus;
import home.match_betting_server.phases.domain.PhaseType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePhaseRequest {
    private String name;
    private PhaseType phaseType;
    private PhaseStatus phaseStatus;
}
