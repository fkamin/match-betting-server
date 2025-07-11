package home.match_betting_server.phases.domain;

import home.match_betting_server.phases.dto.exceptions.InvalidPhaseStatusException;
import home.match_betting_server.phases.dto.exceptions.PhaseNotFoundException;
import home.match_betting_server.phases.dto.exceptions.PhaseWithThatNameAlreadyExistsException;
import home.match_betting_server.phases.dto.requests.CreatePhaseRequest;
import home.match_betting_server.phases.dto.requests.UpdatePhaseNameRequest;
import home.match_betting_server.phases.dto.responses.PhaseDetailedResponse;
import home.match_betting_server.phases.dto.responses.PhaseSimplifiedResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PhaseFacade {
    private final PhaseRepository phaseRepository;

    public PhaseFacade(PhaseRepository phaseRepository) {
        this.phaseRepository = phaseRepository;
    }

    public PhaseSimplifiedResponse addPhase(CreatePhaseRequest createPhaseRequest) {
        if (phaseRepository.existsByName(createPhaseRequest.getName())) throw new PhaseWithThatNameAlreadyExistsException();

        //TODO(Walidacja phase status)
        Phase newPhase = new Phase(
                createPhaseRequest.getName(),
                createPhaseRequest.getPhaseType(),
                createPhaseRequest.getPhaseStatus()
                );

        return phaseRepository.save(newPhase).toSimplifiedResponse();
    }

    public PhaseDetailedResponse getPhase(Long phaseId) {
        return findPhaseById(phaseId).toDetailedResponse();
    }

    public List<PhaseSimplifiedResponse> getAllPhases() {
        return phaseRepository.findAll().stream().map(Phase::toSimplifiedResponse).toList();
    }

    public PhaseSimplifiedResponse updatePhase(Long phaseId, UpdatePhaseNameRequest updatePhaseNameRequest) {
        Phase phase = findPhaseById(phaseId);

        //TODO(Walidacja nowej nazwy)
        if (phaseRepository.existsByName(updatePhaseNameRequest.getName())) throw new PhaseWithThatNameAlreadyExistsException();
        phase.setName(updatePhaseNameRequest.getName());

        return phaseRepository.save(phase).toSimplifiedResponse();
    }

    public PhaseDetailedResponse updatePhaseStatus(Long phaseId, PhaseStatus newPhaseStatus) {
        Phase phase = findPhaseById(phaseId);

        //TODO(zastanowic sie nad walidacja i uwarunkowaniami zmiany statusu fazy)
        switch (newPhaseStatus) {
            case MATCHES_AND_ACCOUNTS_CREATION -> {
                validatePhaseStatus(phase.getPhaseStatus(), newPhaseStatus);
                phase.setPhaseStatus(PhaseStatus.MATCHES_AND_ACCOUNTS_CREATION);
            }
            case USER_BETS_CREATION ->  {
                validatePhaseStatus(phase.getPhaseStatus(), newPhaseStatus);
                phase.setPhaseStatus(PhaseStatus.USER_BETS_CREATION);
            }
            case GAMEPLAY -> {
                validatePhaseStatus(phase.getPhaseStatus(), newPhaseStatus);
                phase.setPhaseStatus(PhaseStatus.GAMEPLAY);
            }
            default -> throw new InvalidPhaseStatusException();
        }

        return phaseRepository.save(phase).toDetailedResponse();
    }

    private void validatePhaseStatus(PhaseStatus currentPhaseStatus, PhaseStatus newPhaseStatus) {
        if (currentPhaseStatus == newPhaseStatus) throw new InvalidPhaseStatusException();
    }

    public ResponseEntity<String> deletePhase(Long phaseId) {
        Phase phaseToDelete = findPhaseById(phaseId);
        phaseRepository.delete(phaseToDelete);

        return ResponseEntity.noContent().build();
    }

    private Phase findPhaseById(Long phaseId) {
        return phaseRepository.findById(phaseId).orElseThrow(PhaseNotFoundException::new);
    }

}
