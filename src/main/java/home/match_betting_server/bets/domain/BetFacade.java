package home.match_betting_server.bets.domain;

import home.match_betting_server.bets.dto.exceptions.BetAlreadyExistsException;
import home.match_betting_server.bets.dto.requests.CreateBetRequest;
import home.match_betting_server.bets.dto.responses.BetSimplifiedResponse;
import home.match_betting_server.matches.domain.Match;
import home.match_betting_server.matches.domain.MatchRepository;
import home.match_betting_server.matches.dto.exceptions.MatchNotFoundException;
import home.match_betting_server.phase_user_stats.domain.PhaseUserStatsRepository;
import home.match_betting_server.phase_user_stats.dto.exceptions.NotAllowedOperationException;
import home.match_betting_server.phase_user_stats.dto.exceptions.UserDoesNotJoinedThatPhaseException;
import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.phases.domain.PhaseRepository;
import home.match_betting_server.phases.domain.PhaseStatus;
import home.match_betting_server.phases.domain.PhaseType;
import home.match_betting_server.phases.dto.exceptions.PhaseNotFoundException;
import home.match_betting_server.users.domain.User;
import home.match_betting_server.users.domain.UserRepository;
import home.match_betting_server.users.dto.exceptions.UserNotFoundException;

public class BetFacade {
    private final BetRepository betRepository;
    private final PhaseRepository phaseRepository;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    private final PhaseUserStatsRepository phaseUserStatsRepository;

    public BetFacade(BetRepository betRepository, PhaseRepository phaseRepository, MatchRepository matchRepository, UserRepository userRepository, PhaseUserStatsRepository phaseUserStatsRepository) {
        this.betRepository = betRepository;
        this.phaseRepository = phaseRepository;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
        this.phaseUserStatsRepository = phaseUserStatsRepository;
    }

    public BetSimplifiedResponse createBet(Long userId, CreateBetRequest createBetRequest) {
        User user = findUserById(userId);
        Match match = findMatchById(createBetRequest.getMatchId());
        Phase phase = findPhaseById(match.getPhase().getId());

        validateBetCreationConditions(user, match, phase);
        if (isMatchInKnockoutStage(phase))
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private Match findMatchById(Long matchId) {
        return matchRepository.findById(matchId).orElseThrow(MatchNotFoundException::new);
    }

    private Phase findPhaseById(Long phaseId) {
        return phaseRepository.findById(phaseId).orElseThrow(PhaseNotFoundException::new);
    }

    private void validateBetCreationConditions(User user, Match match, Phase phase) {
        if (phase.getPhaseStatus() != PhaseStatus.USER_BETS_CREATION) throw new NotAllowedOperationException();
        if (!phaseUserStatsRepository.existsByPhaseAndUser(phase, user)) throw new UserDoesNotJoinedThatPhaseException();
        if (betRepository.existsByUserAndMatch(user, match)) throw new BetAlreadyExistsException();
    }

    private boolean isMatchInKnockoutStage(Phase phase) {
        return phase.getPhaseType() == PhaseType.KNOCKOUT_STAGE;
    }
}
