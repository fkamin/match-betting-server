package home.match_betting_server.phases.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Phase(String name, PhaseType phaseType) {
        this.name = name;
        this.phaseType = phaseType;
    }
}
