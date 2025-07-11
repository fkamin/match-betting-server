package home.match_betting_server.matches.domain;

import home.match_betting_server.matches.dto.exceptions.MatchIsFinishedException;
import home.match_betting_server.matches.dto.exceptions.MatchNotFoundException;
import home.match_betting_server.matches.dto.requests.CreateMatchRequest;
import home.match_betting_server.matches.dto.requests.FinishMatchRequest;
import home.match_betting_server.matches.dto.requests.UpdateMatchRequest;
import home.match_betting_server.matches.dto.responses.MatchDetailedResponse;
import home.match_betting_server.matches.dto.responses.MatchSimplifiedResponse;
import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.phases.domain.PhaseRepository;
import home.match_betting_server.phases.dto.exceptions.PhaseNotFoundException;
import home.match_betting_server.teams.domain.Team;
import home.match_betting_server.teams.domain.TeamRepository;
import home.match_betting_server.teams.dto.exceptions.TeamNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class MatchFacade {
    private final MatchRepository matchRepository;
    private final PhaseRepository phaseRepository;
    private final TeamRepository teamRepository;

    public MatchFacade(MatchRepository matchRepository, PhaseRepository phaseRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.phaseRepository = phaseRepository;
        this.teamRepository = teamRepository;
    }

    public MatchSimplifiedResponse createMatch(CreateMatchRequest createMatchRequest) {
        Phase phase = findPhaseById(createMatchRequest.getPhaseId());
        Team teamLeft = findTeamById(createMatchRequest.getTeamLeftId());
        Team teamRight = findTeamById(createMatchRequest.getTeamRightId());

        //TODO(DODATKOWA LOGIKA NP. NIE MOZE GRAC FRANCJA VS FRANCJA, NIE MOZE GRAC W FAZIE GRUPOWEJ DRUZYNA Z DRUZYNA Z INNEJ GRUPY)
        //TODO(WALIDACJA DATY MECZU NP. MECZ NIE MOZE BYC 27 LIPCA JEZELI JEST 28 LIPIEC BR.)
        Match match = new Match(phase, teamLeft, teamRight, createMatchRequest.getMatchDate());

        return matchRepository.save(match).toSimplifiedResponse();
    }

    public MatchDetailedResponse getMatch(Long matchId) {
        return findMatchById(matchId).toDetailedResponse();
    }

    public List<MatchSimplifiedResponse> getAllMatches(Long phaseId) {
        Phase phase = findPhaseById(phaseId);
        return matchRepository.findAllByPhase(phase).stream().map(Match::toSimplifiedResponse).toList();
    }

    public MatchDetailedResponse updateMatch(Long matchId, UpdateMatchRequest updateMatchRequest) {
        Match matchToUpdate = findMatchById(matchId);
        if (matchToUpdate.isMatchFinished()) throw new MatchIsFinishedException();

        //TODO(WALIDACJA UPDATE_MATCH_REQUEST)
        matchToUpdate.setTeamLeft(findTeamById(updateMatchRequest.getTeamLeftId()));
        matchToUpdate.setTeamRight(findTeamById(updateMatchRequest.getTeamRightId()));
        matchToUpdate.setMatchDate(updateMatchRequest.getMatchDate());

        return matchRepository.save(matchToUpdate).toDetailedResponse();
    }

    public MatchDetailedResponse finishMatch(Long matchId, FinishMatchRequest finishMatchRequest) {
        //TODO(OBSLUGA EVENTU - W PRZYSZLOSCI?)
        Match matchToFinish = findMatchById(matchId);

        //TODO(WALIDACJA FINISH_MATCH_REQUEST)
        matchToFinish.setTeamLeftScore(finishMatchRequest.getTeamLeftScore());
        matchToFinish.setTeamRightScore(finishMatchRequest.getTeamRightScore());
        matchToFinish.finishMatch();

        //TODO(PRZYDZIELANIE PUNKTOW)

        return matchRepository.save(matchToFinish).toDetailedResponse();
    }

    public ResponseEntity<String> deleteMatch(Long matchId) {
        Match matchToDelete = findMatchById(matchId);
        matchRepository.delete(matchToDelete);

        return ResponseEntity.noContent().build();
    }



    private Match findMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow(MatchNotFoundException::new);
    }

    private Phase findPhaseById(Long id) {
        return phaseRepository.findById(id).orElseThrow(PhaseNotFoundException::new);
    }

    private Team findTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
    }

}
