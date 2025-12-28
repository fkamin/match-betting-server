package home.match_betting_server.bets.domain;

import home.match_betting_server.bets.dto.responses.BetDetailedResponse;
import home.match_betting_server.bets.dto.responses.BetSimplifiedResponse;
import home.match_betting_server.matches.domain.Match;
import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.teams.domain.Team;
import home.match_betting_server.users.domain.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "bets")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phase_id")
    private Phase phase;

    @Column(nullable = false)
    private Integer betLeftScore;

    @Column(nullable = false)
    private Integer betRightScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team betWinnerTeam;

    private Integer pointsForBet = null;

    @Column(name = "max_points_in_90_minutes", nullable = false)
    private boolean maxPointsIn90Minutes = false;

    public Bet(User user, Match match, Integer betLeftScore, Integer betRightScore) {
        this.user = user;
        this.match = match;
        this.betLeftScore = betLeftScore;
        this.betRightScore = betRightScore;
    }

    public BetSimplifiedResponse toSimplifiedResponse() {
        return new BetSimplifiedResponse(id, betLeftScore, betRightScore);
    }

    public BetDetailedResponse toDetailedResponse() {
        return new BetDetailedResponse(id, user.getId(), match.getId(), betLeftScore, betRightScore, betWinnerTeam);
    }
}
