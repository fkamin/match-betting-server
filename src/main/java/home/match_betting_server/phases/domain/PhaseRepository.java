package home.match_betting_server.phases.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhaseRepository extends JpaRepository<Phase, Long> {
    boolean existsByName(String name);
}
