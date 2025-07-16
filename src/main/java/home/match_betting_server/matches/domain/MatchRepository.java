package home.match_betting_server.matches.domain;

import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.teams.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByPhase(Phase phase);
    @Query("""
        SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END
        FROM Match m
        WHERE (m.teamLeft = :team1 AND m.teamRight = :team2) OR (m.teamLeft = :team2 AND m.teamRight = :team1)
    """)
    boolean existsByTeamsRegardlessOfSide(@Param("team1") Team team1, @Param("team2") Team team2);
}
