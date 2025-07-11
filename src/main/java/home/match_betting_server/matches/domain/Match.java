package home.match_betting_server.matches.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import home.match_betting_server.matches.dto.responses.MatchDetailedResponse;
import home.match_betting_server.matches.dto.responses.MatchSimplifiedResponse;
import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.phases.domain.PhaseType;
import home.match_betting_server.teams.domain.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "matches")
public class Match {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Phase phase;

    @ManyToOne
    private Team teamLeft;

    @ManyToOne
    private Team teamRight;

    private Integer teamLeftScore;
    private Integer teamRightScore;

    @ManyToOne
    private Team matchWinner;

    private LocalDateTime matchDate;

    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus = MatchStatus.SCHEDULED;

    public Match(Phase phase, Team teamLeft, Team teamRight, LocalDateTime matchDate) {
        this.phase = phase;
        this.teamLeft = teamLeft;
        this.teamRight = teamRight;
        this.matchDate = matchDate;
    }

    public MatchSimplifiedResponse toSimplifiedResponse() {
        return new MatchSimplifiedResponse(id, phase.getId(), teamLeft.getName(), teamRight.getName());
    }

    public MatchDetailedResponse toDetailedResponse() {
        return new MatchDetailedResponse(id, phase.getId(), teamLeft.getName(), teamRight.getName(), teamLeftScore, teamRightScore, matchWinner.getName(), matchDate, matchStatus);
    }

    // --- Pomocnicze metody biznesowe ---

//    public boolean isGroupStage() {
//        return phase.getPhaseType() == PhaseType.GROUP_STAGE;
//    }
//
//    public boolean isKnockoutStage() {
//        return phase.getPhaseType() == PhaseType.KNOCKOUT_STAGE;
//    }
//
//    public boolean isDrawIn90Minutes() {
//        return teamLeftScore != null && teamRightScore != null && teamLeftScore.equals(teamRightScore);
//    }
//
//    public boolean hasWinnerDeclared() {
//        return matchWinner != null;
//    }
}
