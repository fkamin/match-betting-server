package home.match_betting_server.bets.domain;

import home.match_betting_server.bets.dto.exceptions.BetAlreadyExistsException;
import home.match_betting_server.bets.dto.exceptions.BetDoesNotExistsException;
import home.match_betting_server.bets.dto.requests.CreateBetRequest;
import home.match_betting_server.bets.dto.requests.UpdateBetRequest;
import home.match_betting_server.bets.dto.responses.BetDetailedResponse;
import home.match_betting_server.bets.dto.responses.BetSimplifiedResponse;
import home.match_betting_server.matches.domain.Match;
import home.match_betting_server.matches.domain.MatchRepository;
import home.match_betting_server.matches.dto.exceptions.MatchIsFinishedException;
import home.match_betting_server.matches.dto.exceptions.MatchDoesNotExistsException;
import home.match_betting_server.phase_user_stats.domain.PhaseUserStatsRepository;
import home.match_betting_server.phase_user_stats.dto.exceptions.NotAllowedOperationException;
import home.match_betting_server.phase_user_stats.dto.exceptions.UserDoesNotJoinedThatPhaseException;
import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.phases.domain.PhaseRepository;
import home.match_betting_server.phases.domain.PhaseStatus;
import home.match_betting_server.phases.dto.exceptions.PhaseDoesNotExistsException;
import home.match_betting_server.teams.domain.Team;
import home.match_betting_server.teams.domain.TeamRepository;
import home.match_betting_server.teams.dto.exceptions.TeamDoesNotExistsException;
import home.match_betting_server.users.domain.User;
import home.match_betting_server.users.domain.UserRepository;
import home.match_betting_server.users.dto.exceptions.UserDoesNotExistsException;

import java.util.List;

public class BetFacade {
    private final BetRepository betRepository;
    private final PhaseRepository phaseRepository;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    private final PhaseUserStatsRepository phaseUserStatsRepository;
    private final TeamRepository teamRepository;

    public BetFacade(
            BetRepository betRepository,
            PhaseRepository phaseRepository,
            MatchRepository matchRepository,
            UserRepository userRepository,
            PhaseUserStatsRepository phaseUserStatsRepository,
            TeamRepository teamRepository) {
        this.betRepository = betRepository;
        this.phaseRepository = phaseRepository;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
        this.phaseUserStatsRepository = phaseUserStatsRepository;
        this.teamRepository = teamRepository;
    }

    public BetSimplifiedResponse createBet(Long userId, Long matchId, CreateBetRequest createBetRequest) {
        User user = findUserById(userId);
        Match match = findMatchById(matchId);
        Phase phase = match.getPhase();

        validateBetCreationConditions(user, match, phase);

        Bet newBet = new Bet(user, match, createBetRequest.getBetLeftScore(), createBetRequest.getBetRightScore());
        if (phase.isKnockoutStage()) {
            Team betWinnerTeam = findTeamById(createBetRequest.getBetWinnerTeamId());
            newBet.setBetWinnerTeam(betWinnerTeam);
        }

        return betRepository.save(newBet).toSimplifiedResponse();
    }

    public List<BetDetailedResponse> getAllBetsFromUserInPhase(Long userId, Long phaseId) {
        User user = findUserById(userId);
        Phase phase = findPhaseById(phaseId);

        return betRepository.findAllByUserAndMatchPhase(user, phase).stream().map(Bet::toDetailedResponse).toList();
    }

    public BetDetailedResponse getBetById(Long userId, Long matchId, Long betId) {
        //TODO(ZASTANOWIC SIE NAD ZMIANA TEGO)
        findUserById(userId);
        findMatchById(matchId);

        return findBetById(betId).toDetailedResponse();
    }

    public BetSimplifiedResponse updateBet(Long userId, Long matchId, Long betId, UpdateBetRequest updateBetRequest) {
        User user = findUserById(userId);
        Match match = findMatchById(matchId);
        Phase phase = match.getPhase();
        Bet newBet = findBetById(betId);

        validateBetUpdateConditions(user, match, phase);

        newBet.setBetLeftScore(updateBetRequest.getBetLeftScore());
        newBet.setBetRightScore(updateBetRequest.getBetRightScore());
        if (phase.isKnockoutStage()) {
            Team betWinnerTeam = findTeamById(updateBetRequest.getBetWinnerTeamId());
            newBet.setBetWinnerTeam(betWinnerTeam);
        }

        return betRepository.save(newBet).toSimplifiedResponse();
    }






    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserDoesNotExistsException::new);
    }

    private Match findMatchById(Long matchId) {
        return matchRepository.findById(matchId).orElseThrow(MatchDoesNotExistsException::new);
    }

    private Phase findPhaseById(Long phaseId) {
        return phaseRepository.findById(phaseId).orElseThrow(PhaseDoesNotExistsException::new);
    }

    private Bet findBetById(Long betId) {
        return betRepository.findById(betId).orElseThrow(BetDoesNotExistsException::new);
    }

    private Team findTeamById(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(TeamDoesNotExistsException::new);
    }

    private void validateBetCreationConditions(User user, Match match, Phase phase) {
        isPhaseStatusInBetCreation(phase);
        isMatchFinished(match);
        doesUserJoinedPhase(user, phase);
        doesBetAlreadyExists(user, match);
    }

    private void validateBetUpdateConditions(User user, Match match, Phase phase) {
        isPhaseStatusInBetCreation(phase);
        isMatchFinished(match);
        doesUserJoinedPhase(user, phase);
    }

    private void isPhaseStatusInBetCreation(Phase phase) {
        if (phase.getPhaseStatus() != PhaseStatus.USER_BETS_CREATION) throw new NotAllowedOperationException();
    }

    private void isMatchFinished(Match match) {
        if (match.isMatchFinished()) throw new MatchIsFinishedException();
    }

    private void doesUserJoinedPhase(User user, Phase phase) {
        if (!phaseUserStatsRepository.existsByPhaseAndUser(phase, user)) throw new UserDoesNotJoinedThatPhaseException();
    }

    private void doesBetAlreadyExists(User user, Match match) {
        if (betRepository.existsByUserAndMatch(user, match)) throw new BetAlreadyExistsException();
    }
}
