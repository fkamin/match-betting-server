package home.match_betting_server.matches.domain;

import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.teams.domain.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Getter
@Setter
@NoArgsConstructor
public class Match {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phase_id")
    private Phase phase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_left_id")
    private Team teamLeft;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_right_id")
    private Team teamRight;

    private Integer leftScore;
    private Integer rightScore;
    private LocalDateTime matchDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchStatus matchStatus = MatchStatus.SCHEDULED;

    @Enumerated(EnumType.STRING)
    private MatchType matchType;
}
