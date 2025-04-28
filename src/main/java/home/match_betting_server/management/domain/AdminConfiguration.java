package home.match_betting_server.management.domain;

import home.match_betting_server.users.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminConfiguration {

    @Bean
    AdminFacade adminFacade(UserRepository userRepository) {
        return new AdminFacade(userRepository);
    }
}
