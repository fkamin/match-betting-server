package home.match_betting_server.bets;

import home.match_betting_server.bets.domain.BetFacade;
import home.match_betting_server.bets.dto.requests.CreateBetRequest;
import home.match_betting_server.bets.dto.requests.UpdateBetRequest;
import home.match_betting_server.bets.dto.responses.BetDetailedResponse;
import home.match_betting_server.bets.dto.responses.BetSimplifiedResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_1/users")
public class BetController {
    private final BetFacade betFacade;

    public BetController(BetFacade betFacade) {
        this.betFacade = betFacade;
    }

    @PostMapping("/{userId}/matches/{matchId}/bets")
    public BetSimplifiedResponse createBet(@PathVariable Long userId, @PathVariable Long matchId, @RequestBody CreateBetRequest createBetRequest) {
        return betFacade.createBet(userId, matchId, createBetRequest);
    }

    @GetMapping("/{userId}/phases/{phaseId}/bets")
    public List<BetDetailedResponse> getAllBetsFromUserInPhase(@PathVariable Long userId, @PathVariable Long phaseId) {
        return betFacade.getAllBetsFromUserInPhase(userId, phaseId);
    }

    @GetMapping("/{userId}/phases/{phaseId}/bets/{betId}")
    public BetDetailedResponse getBetFromUserInPhase(@PathVariable Long userId, @PathVariable Long phaseId, @PathVariable Long betId) {
        return betFacade.getBetById(userId, phaseId, betId);
    }

    @PutMapping("/{userId}/matches/{matchId}/bets/{betId}")
    public BetSimplifiedResponse updateBet(@PathVariable Long userId, @PathVariable Long matchId, @PathVariable Long betId, UpdateBetRequest updateBetRequest) {
        return betFacade.updateBet(userId, matchId, betId, updateBetRequest);
    }


}
