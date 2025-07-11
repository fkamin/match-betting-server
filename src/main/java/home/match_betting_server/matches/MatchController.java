package home.match_betting_server.matches;

import home.match_betting_server.matches.domain.MatchFacade;
import home.match_betting_server.matches.dto.requests.CreateMatchRequest;
import home.match_betting_server.matches.dto.responses.MatchDetailedResponse;
import home.match_betting_server.matches.dto.responses.MatchSimplifiedResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1_1/matches")
public class MatchController {
    private final MatchFacade matchFacade;

    public MatchController(MatchFacade matchFacade) {
        this.matchFacade = matchFacade;
    }

    @PostMapping
    public MatchSimplifiedResponse createMatch(@RequestBody CreateMatchRequest createMatchRequest) {
        return matchFacade.createMatch(createMatchRequest);
    }
}
