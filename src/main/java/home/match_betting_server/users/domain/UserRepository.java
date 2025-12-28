package home.match_betting_server.users.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByLogin(String login);
    Boolean existsByName(String name);

    Optional<User> findByLogin(String login);
}
