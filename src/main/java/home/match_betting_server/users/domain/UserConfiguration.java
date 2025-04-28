package home.match_betting_server.users.domain;

import home.match_betting_server.auth.domain.PasswordService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    UserFacade userFacade(UserRepository userRepository, PasswordService passwordService) {
        return new UserFacade(userRepository, passwordService);
    }
}
