package home.match_betting_server.phases;

import home.match_betting_server.phases.domain.PhaseFacade;
import home.match_betting_server.phases.dto.requests.CreatePhaseRequest;
import home.match_betting_server.phases.dto.responses.PhaseSimplifiedResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1_1/phases")
public class PhaseController {
    private final PhaseFacade phaseFacade;

    public PhaseController(PhaseFacade phaseFacade) {
        this.phaseFacade = phaseFacade;
    }

    @PostMapping
    public PhaseSimplifiedResponse addPhase(@RequestBody CreatePhaseRequest createPhaseRequest) {
        return phaseFacade.addPhase(createPhaseRequest);
    }
}
