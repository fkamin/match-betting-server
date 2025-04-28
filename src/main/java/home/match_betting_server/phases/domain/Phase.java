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

    private PhaseStatus phaseStatus;
}
