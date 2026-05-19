package home.match_betting_server.users.domain;

import home.match_betting_server.auth.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {

    @Bean
    public UserFacade userFacade(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        return new UserFacade(userRepository, passwordEncoder, jwtService);
    }
}
