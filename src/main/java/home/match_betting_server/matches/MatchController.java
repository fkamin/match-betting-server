package home.match_betting_server.matches;

import home.match_betting_server.matches.domain.MatchFacade;
import home.match_betting_server.matches.dto.requests.CreateMatchRequest;
import home.match_betting_server.matches.dto.requests.FinishMatchRequest;
import home.match_betting_server.matches.dto.requests.UpdateMatchRequest;
import home.match_betting_server.matches.dto.responses.MatchDetailedResponse;
import home.match_betting_server.matches.dto.responses.MatchSimplifiedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{matchId}")
    public MatchDetailedResponse getMatch(@PathVariable Long matchId) {
        return matchFacade.getMatch(matchId);
    }

    @GetMapping
    public List<MatchSimplifiedResponse> getAllMatches(@RequestParam Long phaseId) {
        return matchFacade.getAllMatches(phaseId);
    }

    @PutMapping("/{matchId}")
    public MatchDetailedResponse updateMatch(@PathVariable Long matchId, @RequestBody UpdateMatchRequest updateMatchRequest) {
        return matchFacade.updateMatch(matchId, updateMatchRequest);
    }

    @PutMapping("/{matchId}/finish-match")
    public MatchDetailedResponse finishMatch(@PathVariable Long matchId, @RequestBody FinishMatchRequest finishMatchRequest) {
        return matchFacade.finishMatch(matchId, finishMatchRequest);
    }

    @DeleteMapping("/{matchId}")
    public ResponseEntity<String> deleteMatch(@PathVariable Long matchId) {
        return matchFacade.deleteMatch(matchId);
    }
}
