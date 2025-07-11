package home.match_betting_server.phase_user_stats.domain;

import home.match_betting_server.phase_user_stats.dto.exceptions.UserAlreadyJoinThatPhaseException;
import home.match_betting_server.phase_user_stats.dto.responses.PhaseUserStatsSimplifiedResponse;
import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.phases.domain.PhaseRepository;
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

    public PhaseUserStatsSimplifiedResponse joinPhase(Long phaseId, Long userId) {
        Phase phase = findPhaseById(phaseId);
        User user = findUserById(userId);

        isUserAlreadyInPhase(phase, user);

        PhaseUserStats stats = new PhaseUserStats(user, phase);

        return phaseUserStatsRepository.save(stats).toSimplifiedResponse();
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private Phase findPhaseById(Long phaseId) {
        return phaseRepository.findById(phaseId).orElseThrow(PhaseNotFoundException::new);
    }

    private void isUserAlreadyInPhase(Phase phase, User user) {
        if (phaseUserStatsRepository.existsByPhaseAndUser(phase, user)) throw new UserAlreadyJoinThatPhaseException();
    }
}
