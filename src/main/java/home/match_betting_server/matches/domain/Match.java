package home.match_betting_server.matches.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import home.match_betting_server.matches.dto.responses.MatchDetailedResponse;
import home.match_betting_server.matches.dto.responses.MatchSimplifiedResponse;
import home.match_betting_server.phases.domain.Phase;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "phase_id", nullable = false)
    @JsonBackReference
    private Phase phase;

    @ManyToOne(optional = false)
    @JoinColumn(name = "team_left", nullable = false)
    private Team teamLeft;

    @ManyToOne(optional = false)
    @JoinColumn(name = "team_right", nullable = false)
    private Team teamRight;

    @Column(name = "team_left_score")
    private Integer teamLeftScore;

    @Column(name = "team_right_score")
    private Integer teamRightScore;

    @ManyToOne
    @JoinColumn(name = "match_winner_id")
    private Team matchWinner;

    @Column(name = "match_date", nullable = false)
    private LocalDateTime matchDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "match_status", nullable = false)
    private MatchStatus matchStatus = MatchStatus.SCHEDULED;

    public Match(Phase phase, Team teamLeft, Team teamRight, LocalDateTime matchDate) {
        this.phase = phase;
        this.teamLeft = teamLeft;
        this.teamRight = teamRight;
        this.matchDate = matchDate;
    }

    public Match(Phase phase, Team teamLeft, Team teamRight, LocalDateTime matchDate, MatchStatus status) {
        this.phase = phase;
        this.teamLeft = teamLeft;
        this.teamRight = teamRight;
        this.matchDate = matchDate;
        this.matchStatus = status;
    }

    // --- Metody DTO ---

    public MatchSimplifiedResponse toSimplifiedResponse() {
        return new MatchSimplifiedResponse(
                id,
                phase.getId(),
                teamLeft.getName(),
                teamRight.getName()
        );
    }

    public MatchDetailedResponse toDetailedResponse() {
        return new MatchDetailedResponse(
                id,
                phase.getId(),
                teamLeft.getName(),
                teamRight.getName(),
                teamLeftScore,
                teamRightScore,
                matchWinner != null ? matchWinner.getName() : "",
                matchDate,
                matchStatus
        );
    }

    // --- Logika biznesowa ---

    public void finishMatch() {
        this.matchStatus = MatchStatus.FINISHED;
    }

    public boolean teamExistsInMatch(Team team) {
        return this.teamLeft.equals(team) || this.teamRight.equals(team);
    }

    public boolean isMatchFinished() {
        return matchStatus == MatchStatus.FINISHED;
    }
}
