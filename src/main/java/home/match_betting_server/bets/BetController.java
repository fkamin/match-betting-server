package home.match_betting_server.bets;

import home.match_betting_server.bets.domain.BetFacade;
import home.match_betting_server.bets.dto.requests.CreateBetRequest;
import home.match_betting_server.bets.dto.responses.BetDetailedResponse;
import home.match_betting_server.bets.dto.responses.BetSimplifiedResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1_1/bets")
public class BetController {
    private final BetFacade betFacade;

    public BetController(BetFacade betFacade) {
        this.betFacade = betFacade;
    }

    @PostMapping
    public BetSimplifiedResponse createBet(@RequestParam Long userId, @RequestBody CreateBetRequest createBetRequest) {
        return betFacade.createBet(userId, createBetRequest);
    }
}
