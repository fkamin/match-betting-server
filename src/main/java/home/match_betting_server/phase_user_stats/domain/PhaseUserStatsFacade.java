package home.match_betting_server.phase_user_stats.domain;

import home.match_betting_server.phase_user_stats.dto.exceptions.NotAllowedOperationException;
import home.match_betting_server.phase_user_stats.dto.exceptions.PhaseUserStatsDoesNotExistsException;
import home.match_betting_server.phase_user_stats.dto.exceptions.UserAlreadyJoinThatPhaseException;
import home.match_betting_server.phase_user_stats.dto.responses.PhaseUserStatsDetailedResponse;
import home.match_betting_server.phase_user_stats.dto.responses.PhaseUserStatsSimplifiedResponse;
import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.phases.domain.PhaseRepository;
import home.match_betting_server.phases.domain.PhaseStatus;
import home.match_betting_server.phases.dto.exceptions.PhaseNotFoundException;
import home.match_betting_server.users.domain.User;
import home.match_betting_server.users.domain.UserRepository;
import home.match_betting_server.users.dto.exceptions.UserNotFoundException;

public class PhaseUserStatsFacade {
    private final UserRepository userRepository;
    private final PhaseRepository phaseRepository;
    private final PhaseUserStatsRepository phaseUserStatsRepository;

    public PhaseUserStatsFacade(UserRepository userRepository, PhaseRepository phaseRepository, PhaseUserStatsRepository phaseUserStatsRepository) {
        this.userRepository = userRepository;
        this.phaseRepository = phaseRepository;
        this.phaseUserStatsRepository = phaseUserStatsRepository;
    }

    public PhaseUserStatsSimplifiedResponse joinUserToPhase(Long userId, Long phaseId) {
        User user = findUserById(userId);
        Phase phase = findPhaseById(phaseId);

        if (phase.getPhaseStatus() != PhaseStatus.MATCHES_AND_ACCOUNTS_CREATION) throw new NotAllowedOperationException();
        if (isUserAlreadyInPhase(phase, user)) throw new UserAlreadyJoinThatPhaseException();

        PhaseUserStats stats = new PhaseUserStats(user, phase);

        return phaseUserStatsRepository.save(stats).toSimplifiedResponse();
    }

    public PhaseUserStatsDetailedResponse getUserStats(Long userId, Long phaseId) {
        User user = findUserById(userId);
        Phase phase = findPhaseById(phaseId);

        PhaseUserStats phaseUserStats = phaseUserStatsRepository.findByPhaseAndUser(phase, user).orElseThrow(PhaseUserStatsDoesNotExistsException::new);

        return phaseUserStats.toDetailedResponse();
    }

    //TODO(IN THE FUTURE)
//    public PhaseUserStatsSimplifiedResponse removeUserFromPhase(Long phaseId, Long userId) {
//        Phase phase = findPhaseById(phaseId);
//        if (phase.getPhaseStatus() != PhaseStatus.MATCHES_AND_ACCOUNTS_CREATION) throw new NotAllowedOperationException();
//
//        User user = findUserById(userId);
//        if (isUserAlreadyInPhase(phase, user)) {
//
//        } else throw new UserDoesNotJoinedThatPhaseException();
//    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private Phase findPhaseById(Long phaseId) {
        return phaseRepository.findById(phaseId).orElseThrow(PhaseNotFoundException::new);
    }

    private boolean isUserAlreadyInPhase(Phase phase, User user) {
        return phaseUserStatsRepository.existsByPhaseAndUser(phase, user);
    }
}
