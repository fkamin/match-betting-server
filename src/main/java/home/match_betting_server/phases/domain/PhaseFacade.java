package home.match_betting_server.phases.domain;

import home.match_betting_server.phases.dto.exceptions.*;
import home.match_betting_server.phases.dto.requests.CreatePhaseRequest;
import home.match_betting_server.phases.dto.requests.UpdatePhaseNameRequest;
import home.match_betting_server.phases.dto.responses.PhaseDetailedResponse;
import home.match_betting_server.phases.dto.responses.PhaseSimplifiedResponse;

import java.util.List;

public class PhaseFacade {
    private final PhaseRepository phaseRepository;

    public PhaseFacade(PhaseRepository phaseRepository) {
        this.phaseRepository = phaseRepository;
    }

    public PhaseSimplifiedResponse createPhase(CreatePhaseRequest createPhaseRequest) {
        validatePhaseCreationConditions(createPhaseRequest);

        Phase newPhase = new Phase(
                createPhaseRequest.getName(),
                createPhaseRequest.getPhaseType(),
                createPhaseRequest.getPhaseStatus()
        );

        return phaseRepository.save(newPhase).toSimplifiedResponse();
    }

    public List<PhaseSimplifiedResponse> getAllPhases() {
        return phaseRepository.findAll().stream().map(Phase::toSimplifiedResponse).toList();
    }

    public PhaseDetailedResponse getPhaseDetails(Long phaseId) {
        return findPhaseById(phaseId).toDetailedResponse();
    }

    public PhaseSimplifiedResponse updatePhaseName(Long phaseId, UpdatePhaseNameRequest updatePhaseNameRequest) {
        Phase phase = findPhaseById(phaseId);

        validatePhaseExistence(updatePhaseNameRequest.getName());
        validatePhaseName(updatePhaseNameRequest.getName());

        phase.setName(updatePhaseNameRequest.getName());

        return phaseRepository.save(phase).toSimplifiedResponse();
    }

    public void deletePhase(Long phaseId) {
        phaseRepository.delete(findPhaseById(phaseId));
    }

    public void matchesAndAccounts(Long phaseId) {
        Phase phase = findPhaseById(phaseId);

        phase.setPhaseStatus(PhaseStatus.MATCHES_AND_ACCOUNTS_CREATION);

        phaseRepository.save(phase);
    }

    public void userBets(Long phaseId) {
        Phase phase = findPhaseById(phaseId);

        phase.setPhaseStatus(PhaseStatus.USER_BETS_CREATION);

        phaseRepository.save(phase);
    }

    public void gameplay(Long phaseId) {
        Phase phase = findPhaseById(phaseId);

        phase.setPhaseStatus(PhaseStatus.GAMEPLAY);

        phaseRepository.save(phase);
    }

    private void validatePhaseCreationConditions(CreatePhaseRequest createPhaseRequest) {
        validatePhaseExistence(createPhaseRequest.getName());
        validatePhaseType(createPhaseRequest.getPhaseType());
        validatePhaseStatus(createPhaseRequest.getPhaseStatus());
        validatePhaseName(createPhaseRequest.getName());
    }

    private void validatePhaseExistence(String name) {
        if (phaseRepository.existsByName(name)) throw new PhaseAlreadyExistsException();
    }

    private void validatePhaseType(PhaseType phaseType) {
        if (phaseType != PhaseType.KNOCKOUT_STAGE && phaseType != PhaseType.GROUP_STAGE) throw new InvalidPhaseTypeException();
    }

    private void validatePhaseStatus(PhaseStatus phaseStatus) {
        if (phaseStatus != PhaseStatus.MATCHES_AND_ACCOUNTS_CREATION
                && phaseStatus != PhaseStatus.USER_BETS_CREATION
                && phaseStatus != PhaseStatus.GAMEPLAY) throw new InvalidPhaseStatusException();
    }

    private void validatePhaseName(String name) {
        if (name.isEmpty() || name == null) throw new PhaseNameCanNotBeNullOrEmptyException();
    }

    private Phase findPhaseById(Long phaseId) {
        return phaseRepository.findById(phaseId).orElseThrow(PhaseDoesNotExistsException::new);
    }
}
