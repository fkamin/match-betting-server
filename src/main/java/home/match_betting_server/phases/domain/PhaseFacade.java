package home.match_betting_server.phases.domain;

import home.match_betting_server.phases.dto.exceptions.PhaseWithThatNameAlreadyExistsException;
import home.match_betting_server.phases.dto.requests.CreatePhaseRequest;
import home.match_betting_server.phases.dto.responses.PhaseSimplifiedResponse;

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

}
