package home.match_betting_server.phase_user_stats;

import home.match_betting_server.phase_user_stats.domain.PhaseUserStatsFacade;
import home.match_betting_server.phase_user_stats.dto.responses.PhaseUserStatsDetailedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1_1/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PhaseUserStatsController {
    private final PhaseUserStatsFacade phaseUserStatsFacade;

    @PostMapping("/{userId}/phases/{phaseId}/join-user")
    public ResponseEntity<String> joinUserToPhase(@PathVariable Long userId, @PathVariable Long phaseId) {
        phaseUserStatsFacade.joinUserToPhase(userId, phaseId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/phases/{phaseId}/remove-user")
    public ResponseEntity<String> removeUserFromPhase(@PathVariable Long userId, @PathVariable Long phaseId) {
        phaseUserStatsFacade.removeUserFromPhase(userId, phaseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/phases/{phaseId}/stats")
    public PhaseUserStatsDetailedResponse getUserStats(@PathVariable Long userId, @PathVariable Long phaseId) {
        return phaseUserStatsFacade.getUserStats(userId, phaseId);
    }
}
