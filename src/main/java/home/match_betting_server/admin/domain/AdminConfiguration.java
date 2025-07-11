package home.match_betting_server.admin.domain;

import home.match_betting_server.auth.domain.PasswordService;
import home.match_betting_server.users.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminConfiguration {

    @Bean
    AdminFacade adminFacade(UserRepository userRepository, PasswordService passwordService) {
        return new AdminFacade(userRepository, passwordService);
    }
}
