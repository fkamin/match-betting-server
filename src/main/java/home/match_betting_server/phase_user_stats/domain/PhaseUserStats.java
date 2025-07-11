package home.match_betting_server.phase_user_stats.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import home.match_betting_server.phase_user_stats.dto.responses.PhaseUserStatsSimplifiedResponse;
import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.users.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "phase_user_stats")
public class PhaseUserStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToOne
    @JsonBackReference
    private Phase phase;

    private int points;
    private int betsWithMaxScore;
    private double percentageOfCorrectGuesses;
    private int rankingPosition;

    public PhaseUserStats(User user, Phase phase) {
        this.user = user;
        this.phase = phase;
        this.points = 0;
        this.betsWithMaxScore = 0;
        this.percentageOfCorrectGuesses = 0;
        this.rankingPosition = 0;
    }

    public PhaseUserStatsSimplifiedResponse toSimplifiedResponse() {
        return new PhaseUserStatsSimplifiedResponse(id, user.getId(), phase.getId());
    }
}
