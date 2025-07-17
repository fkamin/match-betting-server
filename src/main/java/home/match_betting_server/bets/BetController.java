package home.match_betting_server.bets;

import home.match_betting_server.bets.domain.BetFacade;
import home.match_betting_server.bets.dto.requests.CreateBetRequest;
import home.match_betting_server.bets.dto.requests.UpdateBetRequest;
import home.match_betting_server.bets.dto.responses.BetDetailedResponse;
import home.match_betting_server.bets.dto.responses.BetSimplifiedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_1/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BetController {
    private final BetFacade betFacade;

    @PostMapping("/{userId}/matches/{matchId}/bets")
    public BetSimplifiedResponse createBet(@PathVariable Long userId, @PathVariable Long matchId, @RequestBody CreateBetRequest createBetRequest) {
        return betFacade.createBet(userId, matchId, createBetRequest);
    }

    @GetMapping("/{userId}/phases/{phaseId}/bets")
    public List<BetDetailedResponse> getAllBetsFromUserInPhase(@PathVariable Long userId, @PathVariable Long phaseId) {
        return betFacade.getAllBetsFromUserInPhase(userId, phaseId);
    }

    @GetMapping("/{userId}/matches/{matchId}/bets/{betId}")
    public BetDetailedResponse getBetFromUserInMatch(@PathVariable Long userId, @PathVariable Long matchId, @PathVariable Long betId) {
        return betFacade.getBetById(userId, matchId, betId);
    }

    @PutMapping("/{userId}/matches/{matchId}/bets/{betId}")
    public BetSimplifiedResponse updateBet(@PathVariable Long userId, @PathVariable Long matchId, @PathVariable Long betId, @RequestBody UpdateBetRequest updateBetRequest) {
        return betFacade.updateBet(userId, matchId, betId, updateBetRequest);
    }


}
