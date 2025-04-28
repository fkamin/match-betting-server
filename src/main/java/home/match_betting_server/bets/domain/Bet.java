package home.match_betting_server.bets.domain;

import home.match_betting_server.matches.domain.Match;
import home.match_betting_server.users.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bets")
@Getter
@Setter
@NoArgsConstructor
public class Bet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @Column(nullable = false)
    private Integer betLeftScore;

    @Column(nullable = false)
    private Integer betRightScore;

    private Integer pointsForBet = 0;
}
