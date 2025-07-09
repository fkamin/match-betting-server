package home.match_betting_server.users.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByLogin(String login);
    Boolean existsByName(String name);
}
