package home.match_betting_server.phase_user_stats.domain;

import home.match_betting_server.phase_user_stats.dto.exceptions.NotAllowedOperationException;
import home.match_betting_server.phase_user_stats.dto.exceptions.PhaseUserStatsDoesNotExistsException;
import home.match_betting_server.phase_user_stats.dto.exceptions.UserAlreadyJoinThatPhaseException;
import home.match_betting_server.phase_user_stats.dto.responses.PhaseUserStatsDetailedResponse;
import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.phases.domain.PhaseRepository;
import home.match_betting_server.phases.domain.PhaseStatus;
import home.match_betting_server.phases.dto.exceptions.PhaseDoesNotExistsException;
import home.match_betting_server.users.domain.User;
import home.match_betting_server.users.domain.UserRepository;
import home.match_betting_server.users.dto.exceptions.UserDoesNotExistsException;

public class PhaseUserStatsFacade {
    private final UserRepository userRepository;
    private final PhaseRepository phaseRepository;
    private final PhaseUserStatsRepository phaseUserStatsRepository;

    public PhaseUserStatsFacade(UserRepository userRepository, PhaseRepository phaseRepository, PhaseUserStatsRepository phaseUserStatsRepository) {
        this.userRepository = userRepository;
        this.phaseRepository = phaseRepository;
        this.phaseUserStatsRepository = phaseUserStatsRepository;
    }

    public void joinUserToPhase(Long userId, Long phaseId) {
        User user = findUserById(userId);
        Phase phase = findPhaseById(phaseId);

        validatePhaseJoinabilityForUser(user, phase);

        PhaseUserStats newPhaseUserStats = new PhaseUserStats(user, phase);

        phaseUserStatsRepository.save(newPhaseUserStats).toSimplifiedResponse();
    }

    public PhaseUserStatsDetailedResponse getUserStats(Long userId, Long phaseId) {
        User user = findUserById(userId);
        Phase phase = findPhaseById(phaseId);

        return findByUserAndPhase(user, phase).toDetailedResponse();
    }

    public void removeUserFromPhase(Long userId, Long phaseId) {
        PhaseUserStats phaseUserStats = findByUserAndPhase(findUserById(userId), findPhaseById(phaseId));

        validatePhaseRemovabilityForUser(phaseUserStats.getUser(), phaseUserStats.getPhase());

        phaseUserStatsRepository.delete(phaseUserStats);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserDoesNotExistsException::new);
    }

    private Phase findPhaseById(Long phaseId) {
        return phaseRepository.findById(phaseId).orElseThrow(PhaseDoesNotExistsException::new);
    }

    private void validatePhaseJoinabilityForUser(User user, Phase phase) {
        validatePhaseStatus(phase.getPhaseStatus());
        validateUserExistenceInPhase(user, phase);
    }

    private void validatePhaseStatus(PhaseStatus phaseStatus) {
        if (phaseStatus != PhaseStatus.MATCHES_AND_ACCOUNTS_CREATION) throw new NotAllowedOperationException();
    }

    private void validateUserExistenceInPhase(User user, Phase phase) {
        if (phaseUserStatsRepository.existsByUserAndPhase(user, phase)) throw new UserAlreadyJoinThatPhaseException();
    }

    private PhaseUserStats findByUserAndPhase(User user, Phase phase) {
        return phaseUserStatsRepository.findByUserAndPhase(user, phase).orElseThrow(PhaseUserStatsDoesNotExistsException::new);
    }

    private void validatePhaseRemovabilityForUser(User user, Phase phase) {
        validatePhaseStatus(phase.getPhaseStatus());
        if (phaseUserStatsRepository.existsByUserAndPhase(user, phase)) throw new UserAlreadyJoinThatPhaseException();
    }
}
