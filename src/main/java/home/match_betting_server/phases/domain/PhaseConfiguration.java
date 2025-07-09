package home.match_betting_server.phases.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PhaseConfiguration {

    @Bean
    public PhaseFacade phaseFacade(PhaseRepository phaseRepository) {
        return new PhaseFacade(phaseRepository);
    }
}
