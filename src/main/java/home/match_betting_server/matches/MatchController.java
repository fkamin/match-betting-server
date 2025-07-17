package home.match_betting_server.matches;

import home.match_betting_server.matches.domain.MatchFacade;
import home.match_betting_server.matches.dto.requests.CreateMatchRequest;
import home.match_betting_server.matches.dto.requests.FinishMatchRequest;
import home.match_betting_server.matches.dto.requests.UpdateMatchRequest;
import home.match_betting_server.matches.dto.responses.MatchDetailedResponse;
import home.match_betting_server.matches.dto.responses.MatchSimplifiedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_1/phases")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MatchController {
    private final MatchFacade matchFacade;

    @PostMapping("/{phaseId}/matches")
    public MatchSimplifiedResponse createMatch(@PathVariable Long phaseId, @RequestBody CreateMatchRequest createMatchRequest) {
        return matchFacade.createMatch(phaseId, createMatchRequest);
    }

    @GetMapping("/{phaseId}/matches/{matchId}")
    public MatchDetailedResponse getMatch(@PathVariable Long phaseId, @PathVariable Long matchId) {
        return matchFacade.getMatch(phaseId, matchId);
    }

    @GetMapping("/{phaseId}/matches")
    public List<MatchSimplifiedResponse> getAllMatches(@PathVariable Long phaseId) {
        return matchFacade.getAllMatches(phaseId);
    }

    @PutMapping("/{phaseId}/matches/{matchId}")
    public MatchDetailedResponse updateMatch(@PathVariable Long phaseId, @PathVariable Long matchId, @RequestBody UpdateMatchRequest updateMatchRequest) {
        return matchFacade.updateMatch(phaseId, matchId, updateMatchRequest);
    }

    @PutMapping("/{phaseId}/matches/{matchId}/finish-match")
    public MatchDetailedResponse finishMatch(@PathVariable Long phaseId, @PathVariable Long matchId, @RequestBody FinishMatchRequest finishMatchRequest) {
        return matchFacade.finishMatch(phaseId, matchId, finishMatchRequest);
    }

    @DeleteMapping("/{phaseId}/matches/{matchId}")
    public ResponseEntity<String> deleteMatch(@PathVariable Long phaseId, @PathVariable Long matchId) {
        return matchFacade.deleteMatch(phaseId, matchId);
    }
}
