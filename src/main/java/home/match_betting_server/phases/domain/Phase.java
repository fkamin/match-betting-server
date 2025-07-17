package home.match_betting_server.phases.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import home.match_betting_server.matches.domain.Match;
import home.match_betting_server.phases.dto.responses.PhaseDetailedResponse;
import home.match_betting_server.phases.dto.responses.PhaseSimplifiedResponse;
import home.match_betting_server.phase_user_stats.domain.PhaseUserStats;
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
    @JsonManagedReference
    private List<Match> matches = new ArrayList<>();

    @OneToMany(mappedBy = "phase", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PhaseUserStats> phaseUserStats = new ArrayList<>();

    public Phase(String name, PhaseType phaseType, PhaseStatus phaseStatus) {
        this.name = name;
        this.phaseType = phaseType;
        this.phaseStatus = phaseStatus;
    }

    public PhaseSimplifiedResponse toSimplifiedResponse() {
        return new PhaseSimplifiedResponse(id, name);
    }

    public PhaseDetailedResponse toDetailedResponse() {
        return new PhaseDetailedResponse(id, name, phaseType, phaseStatus);
    }

    public boolean isKnockoutStage() {
        return this.phaseType == PhaseType.KNOCKOUT_STAGE;
    }

    public boolean isGroupStage() {
        return this.phaseType == PhaseType.GROUP_STAGE;
    }

    public boolean isPhaseInMatchesAndUsersCreation() {
        return this.phaseStatus == PhaseStatus.MATCHES_AND_ACCOUNTS_CREATION;
    }

    public boolean isPhaseInGameplayStatus() {
        return this.phaseStatus == PhaseStatus.GAMEPLAY;
    }

}
