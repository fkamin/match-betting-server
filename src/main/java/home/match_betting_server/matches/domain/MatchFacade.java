package home.match_betting_server.matches.domain;

import home.match_betting_server.matches.dto.exceptions.MatchIsFinishedException;
import home.match_betting_server.matches.dto.exceptions.MatchDoesNotExistsException;
import home.match_betting_server.matches.dto.exceptions.TeamsMustBeDifferentException;
import home.match_betting_server.matches.dto.exceptions.TeamsMustBeFromTheSameGroupException;
import home.match_betting_server.matches.dto.requests.CreateMatchRequest;
import home.match_betting_server.matches.dto.requests.FinishMatchRequest;
import home.match_betting_server.matches.dto.requests.UpdateMatchRequest;
import home.match_betting_server.matches.dto.responses.MatchDetailedResponse;
import home.match_betting_server.matches.dto.responses.MatchSimplifiedResponse;
import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.phases.domain.PhaseRepository;
import home.match_betting_server.phases.dto.exceptions.InvalidPhaseStatusException;
import home.match_betting_server.phases.dto.exceptions.PhaseNotFoundException;
import home.match_betting_server.teams.domain.Group;
import home.match_betting_server.teams.domain.Team;
import home.match_betting_server.teams.domain.TeamRepository;
import home.match_betting_server.teams.dto.exceptions.TeamDoesNotExistsException;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public class MatchFacade {
    private final MatchRepository matchRepository;
    private final PhaseRepository phaseRepository;
    private final TeamRepository teamRepository;
    private final ScoringService scoringService;

    public MatchFacade(MatchRepository matchRepository, PhaseRepository phaseRepository, TeamRepository teamRepository, ScoringService scoringService) {
        this.matchRepository = matchRepository;
        this.phaseRepository = phaseRepository;
        this.teamRepository = teamRepository;
        this.scoringService = scoringService;
    }

    public MatchSimplifiedResponse createMatch(Long phaseId, CreateMatchRequest createMatchRequest) {
        Phase phase = findPhaseById(phaseId);
        Team teamLeft = findTeamById(createMatchRequest.getTeamLeftId());
        Team teamRight = findTeamById(createMatchRequest.getTeamRightId());
        LocalDateTime matchDate = createMatchRequest.getMatchDate();

        Match newMatch = validateMatchCreationConditionsAndReturn(phase, teamLeft, teamRight, matchDate);

        return matchRepository.save(newMatch).toSimplifiedResponse();
    }

    private Match validateMatchCreationConditionsAndReturn(Phase phase, Team teamLeft, Team teamRight, LocalDateTime matchDate) {
        isPhaseInMatchesAndAccountsCreationStatus(phase);
        areTwoTeamsDifferent(teamLeft, teamRight);

        if (phase.isGroupStage()) {
            areTwoTeamsFromTheSameGroup(teamLeft.getGroup(), teamRight.getGroup());
        }

        return new Match(phase, teamLeft, teamRight, matchDate);
    }

    public MatchDetailedResponse getMatch(Long phaseId, Long matchId) {
        findPhaseById(phaseId);
        return findMatchById(matchId).toDetailedResponse();
    }

    public List<MatchSimplifiedResponse> getAllMatches(Long phaseId) {
        Phase phase = findPhaseById(phaseId);
        return matchRepository.findAllByPhase(phase).stream().map(Match::toSimplifiedResponse).toList();
    }

    public MatchDetailedResponse updateMatch(Long phaseId, Long matchId, UpdateMatchRequest updateMatchRequest) {
        findPhaseById(phaseId);
        Match matchToUpdate = findMatchById(matchId);
        if (matchToUpdate.isMatchFinished()) throw new MatchIsFinishedException();

        //TODO(WALIDACJA UPDATE_MATCH_REQUEST)

        areTwoTeamsDifferent();

        matchToUpdate.setTeamLeft(findTeamById(updateMatchRequest.getTeamLeftId()));
        matchToUpdate.setTeamRight(findTeamById(updateMatchRequest.getTeamRightId()));
        matchToUpdate.setMatchDate(updateMatchRequest.getMatchDate());

        return matchRepository.save(matchToUpdate).toDetailedResponse();
    }

    public MatchDetailedResponse finishMatch(Long phaseId, Long matchId, FinishMatchRequest finishMatchRequest) {
        findPhaseById(phaseId);
        //TODO(OBSLUGA EVENTU - W PRZYSZLOSCI?)
        Match matchToFinish = findMatchById(matchId);

        //TODO(WALIDACJA FINISH_MATCH_REQUEST)
        if (matchToFinish.isMatchFinished()) throw new MatchIsFinishedException();
        if (finishMatchRequest.getTeamLeftScore() >= 0 && finishMatchRequest.getTeamRightScore() >= 0) {
            matchToFinish.setTeamLeftScore(finishMatchRequest.getTeamLeftScore());
            matchToFinish.setTeamRightScore(finishMatchRequest.getTeamRightScore());
        }

        if (matchToFinish.getPhase().isKnockoutStage()) {
            Team winnerTeam = findTeamById(finishMatchRequest.getWinnerTeamId());
            matchToFinish.setMatchWinner(winnerTeam);
        }

        matchToFinish.finishMatch();

        //TODO(PRZYDZIELANIE PUNKTOW)
        scoringService.assignPointsForMatch(matchToFinish);

        return matchRepository.save(matchToFinish).toDetailedResponse();
    }

    public ResponseEntity<String> deleteMatch(Long phaseId, Long matchId) {
        findPhaseById(phaseId);
        Match matchToDelete = findMatchById(matchId);
        matchRepository.delete(matchToDelete);

        return ResponseEntity.noContent().build();
    }



    private Match findMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow(MatchDoesNotExistsException::new);
    }

    private Phase findPhaseById(Long id) {
        return phaseRepository.findById(id).orElseThrow(PhaseNotFoundException::new);
    }

    private Team findTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow(TeamDoesNotExistsException::new);
    }

    private void areTwoTeamsDifferent(Team teamLeft, Team teamRight) {
        if (teamLeft.equals(teamRight)) throw new TeamsMustBeDifferentException();
    }

    private void areTwoTeamsFromTheSameGroup(Group groupLeft, Group groupRight) {
        if (!groupLeft.equals(groupRight)) throw new TeamsMustBeFromTheSameGroupException();
    }

    private void isPhaseInMatchesAndAccountsCreationStatus(Phase phase) {
        if (!phase.isPhaseInMatchesAndUsersCreation()) throw new InvalidPhaseStatusException();
    }

}
