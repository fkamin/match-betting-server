package home.match_betting_server.phases.domain;

import home.match_betting_server.matches.domain.Match;
import home.match_betting_server.phases.dto.responses.PhaseSimplifiedResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "phases")
@Getter
@Setter
@NoArgsConstructor
public class Phase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PhaseType phaseType;

    @Enumerated(EnumType.STRING)
    private PhaseStatus phaseStatus;

    @OneToMany(mappedBy = "phase", cascade = CascadeType.ALL)
    private List<Match> matches = new ArrayList<>();

    @OneToMany(mappedBy = "phase", cascade = CascadeType.ALL)
    private List<UserPhaseStats> userPhaseStats = new ArrayList<>();

    public Phase(String name, PhaseType phaseType, PhaseStatus phaseStatus) {
        this.name = name;
        this.phaseType = phaseType;
        this.phaseStatus = phaseStatus;
    }

    public PhaseSimplifiedResponse toSimplifiedResponse() {
        return new PhaseSimplifiedResponse(id, name);
    }
}
