package home.match_betting_server.phases.domain;

public class PhaseFacade {
    private final PhaseRepository phaseRepository;

    public PhaseFacade(PhaseRepository phaseRepository) {
        this.phaseRepository = phaseRepository;
    }
}
