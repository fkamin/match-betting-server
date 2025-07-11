package home.match_betting_server.phase_user_stats;

import home.match_betting_server.phase_user_stats.domain.PhaseUserStatsFacade;
import home.match_betting_server.phase_user_stats.dto.responses.PhaseUserStatsSimplifiedResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1_1/phases")
public class PhaseUserStatsController {
    private final PhaseUserStatsFacade phaseUserStatsFacade;

    public PhaseUserStatsController(PhaseUserStatsFacade phaseUserStatsFacade) {
        this.phaseUserStatsFacade = phaseUserStatsFacade;
    }

    @PostMapping("/{phaseId}/join-user")
    public PhaseUserStatsSimplifiedResponse joinUserToPhase(@PathVariable Long phaseId, @RequestParam Long userId) {
        return phaseUserStatsFacade.joinUserToPhase(phaseId, userId);
    }

    //TODO(IN THE FUTURE)
//    @PostMapping("/{phaseId}/remove-user")
//    public PhaseUserStatsSimplifiedResponse removeUserFromPhase(@PathVariable Long phaseId, @RequestParam Long userId) {
//        return phaseUserStatsFacade.removeUserFromPhase(phaseId, userId);
//    }
}
