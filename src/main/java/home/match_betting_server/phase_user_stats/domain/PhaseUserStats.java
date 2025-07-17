package home.match_betting_server.phase_user_stats.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import home.match_betting_server.phase_user_stats.dto.responses.PhaseUserStatsDetailedResponse;
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

    private int points = 0;
    private int betsWithMaxScore = 0;
    private int percentageOfCorrectGuesses = 0;
    private int rankingPosition = 0;

    public PhaseUserStats(User user, Phase phase) {
        this.user = user;
        this.phase = phase;
    }

    public PhaseUserStatsSimplifiedResponse toSimplifiedResponse() {
        return new PhaseUserStatsSimplifiedResponse(id, user.getId(), phase.getId());
    }

    public PhaseUserStatsDetailedResponse toDetailedResponse() {
        return new PhaseUserStatsDetailedResponse(id, user.getId(), phase.getId(), points, betsWithMaxScore, percentageOfCorrectGuesses, rankingPosition);
    }
}
