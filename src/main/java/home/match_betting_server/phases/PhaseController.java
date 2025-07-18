package home.match_betting_server.phases;

import home.match_betting_server.phases.domain.PhaseFacade;
import home.match_betting_server.phases.dto.requests.CreatePhaseRequest;
import home.match_betting_server.phases.dto.requests.UpdatePhaseNameRequest;
import home.match_betting_server.phases.dto.responses.PhaseDetailedResponse;
import home.match_betting_server.phases.dto.responses.PhaseSimplifiedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_1/phases")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PhaseController {
    private final PhaseFacade phaseFacade;

    @PostMapping
    public PhaseSimplifiedResponse createPhase(@RequestBody CreatePhaseRequest createPhaseRequest) {
        return phaseFacade.createPhase(createPhaseRequest);
    }

    @GetMapping
    public List<PhaseSimplifiedResponse> getAllPhases() {
        return phaseFacade.getAllPhases();
    }

    @GetMapping("/{phaseId}")
    public PhaseDetailedResponse getPhaseDetails(@PathVariable Long phaseId) {
        return phaseFacade.getPhaseDetails(phaseId);
    }

    @PutMapping("/{phaseId}/change-name")
    public PhaseSimplifiedResponse updatePhaseName(@PathVariable Long phaseId, @RequestBody UpdatePhaseNameRequest updatePhaseNameRequest) {
        return phaseFacade.updatePhaseName(phaseId, updatePhaseNameRequest);
    }

    @DeleteMapping("/{phaseId}")
    public ResponseEntity<String> deletePhase(@PathVariable Long phaseId) {
        phaseFacade.deletePhase(phaseId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{phaseId}/status/matches-and-accounts")
    public ResponseEntity<String> setPhaseStatusToMatchesAndAccounts(@PathVariable Long phaseId) {
        phaseFacade.matchesAndAccounts(phaseId);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/{phaseId}/status/user-bets")
    public ResponseEntity<String> setPhaseStatusToUserBets(@PathVariable Long phaseId) {
        phaseFacade.userBets(phaseId);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/{phaseId}/status/gameplay")
    public ResponseEntity<String> setPhaseStatusToGameplay(@PathVariable Long phaseId) {
        phaseFacade.gameplay(phaseId);
        return ResponseEntity.accepted().build();
    }

}
