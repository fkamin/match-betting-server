package home.match_betting_server.phases.domain;

import home.match_betting_server.users.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_phase_stats")
public class UserPhaseStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Phase phase;

    private int points;
    private int betsWithMaxScore;
    private double percentageOfCorrectGuesses;
    private int rankingPosition;

    public UserPhaseStats(User user, Phase phase) {
        this.user = user;
        this.phase = phase;
        this.points = 0;
        this.betsWithMaxScore = 0;
        this.percentageOfCorrectGuesses = 0;
        this.rankingPosition = 0;
    }
}
