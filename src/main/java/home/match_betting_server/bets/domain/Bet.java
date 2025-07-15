package home.match_betting_server.bets.domain;

import home.match_betting_server.bets.dto.responses.BetDetailedResponse;
import home.match_betting_server.bets.dto.responses.BetSimplifiedResponse;
import home.match_betting_server.matches.domain.Match;
import home.match_betting_server.teams.domain.Team;
import home.match_betting_server.users.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bets")
public class Bet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @Column(nullable = false)
    private Integer betLeftScore;

    @Column(nullable = false)
    private Integer betRightScore;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team betWinnerTeam;

    private Integer pointsForBet = null;

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
