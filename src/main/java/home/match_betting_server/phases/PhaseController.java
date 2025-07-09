package home.match_betting_server.phases;

import home.match_betting_server.phases.domain.PhaseFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/phases")
@AllArgsConstructor
public class PhaseController {
    private final PhaseFacade phaseFacade;

    @PostMapping
    public void addPhase() {

    }
}
