package home.match_betting_server.phase_user_stats;

import home.match_betting_server.phase_user_stats.domain.PhaseUserStatsFacade;
import home.match_betting_server.phase_user_stats.dto.responses.PhaseUserStatsDetailedResponse;
import home.match_betting_server.phase_user_stats.dto.responses.PhaseUserStatsSimplifiedResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1_1/users")
public class PhaseUserStatsController {
    private final PhaseUserStatsFacade phaseUserStatsFacade;

    public PhaseUserStatsController(PhaseUserStatsFacade phaseUserStatsFacade) {
        this.phaseUserStatsFacade = phaseUserStatsFacade;
    }

    @PostMapping("/{userId}/phases/{phaseId}/join-user")
    public PhaseUserStatsSimplifiedResponse joinUserToPhase(@PathVariable Long userId, @PathVariable Long phaseId) {
        return phaseUserStatsFacade.joinUserToPhase(userId, phaseId);
    }

    @GetMapping("/{userId}/phases/{phaseId}/stats")
    public PhaseUserStatsDetailedResponse getUserStats(@PathVariable Long userId, @PathVariable Long phaseId) {
        return phaseUserStatsFacade.getUserStats(userId, phaseId);
    }

    //TODO(IN THE FUTURE)
//    @PostMapping("/{phaseId}/remove-user")
//    public PhaseUserStatsSimplifiedResponse removeUserFromPhase(@PathVariable Long phaseId, @RequestParam Long userId) {
//        return phaseUserStatsFacade.removeUserFromPhase(phaseId, userId);
//    }
}
