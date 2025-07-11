package home.match_betting_server.phases;

import home.match_betting_server.phases.domain.PhaseFacade;
import home.match_betting_server.phases.domain.PhaseStatus;
import home.match_betting_server.phases.dto.requests.CreatePhaseRequest;
import home.match_betting_server.phases.dto.requests.UpdatePhaseNameRequest;
import home.match_betting_server.phases.dto.responses.PhaseDetailedResponse;
import home.match_betting_server.phases.dto.responses.PhaseSimplifiedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{phaseId}")
    public PhaseDetailedResponse getPhase(@PathVariable Long phaseId) {
        return phaseFacade.getPhase(phaseId);
    }

    @GetMapping
    public List<PhaseSimplifiedResponse> getAllPhases() {
        return phaseFacade.getAllPhases();
    }

    @PutMapping("/{phaseId}/change-name")
    public PhaseSimplifiedResponse updatePhase(@PathVariable Long phaseId, @RequestBody UpdatePhaseNameRequest updatePhaseNameRequest) {
        return phaseFacade.updatePhase(phaseId, updatePhaseNameRequest);
    }

    @PutMapping("/{phaseId}/status/matches-and-accounts")
    public PhaseDetailedResponse setPhaseStatusToMatchesAndAccounts(@PathVariable Long phaseId) {
        return phaseFacade.updatePhaseStatus(phaseId, PhaseStatus.MATCHES_AND_ACCOUNTS_CREATION);
    }

    @PutMapping("/{phaseId}/status/user-bets")
    public PhaseDetailedResponse setPhaseStatusToUserBets(@PathVariable Long phaseId) {
        return phaseFacade.updatePhaseStatus(phaseId, PhaseStatus.USER_BETS_CREATION);
    }

    @PutMapping("/{phaseId}/status/gameplay")
    public PhaseDetailedResponse setPhaseStatusToGameplay(@PathVariable Long phaseId) {
        return phaseFacade.updatePhaseStatus(phaseId, PhaseStatus.GAMEPLAY);
    }

    @DeleteMapping("/{phaseId}")
    public ResponseEntity<String> deletePhase(@PathVariable Long phaseId) {
        return phaseFacade.deletePhase(phaseId);
    }
}
