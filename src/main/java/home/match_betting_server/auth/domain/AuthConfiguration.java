package home.match_betting_server.auth.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class AuthConfiguration {
    @Bean
    public AuthFacade authFacade(UserDetailsService userDetailsService, PasswordService passwordService, JwtService jwtService) {
        return new AuthFacade(userDetailsService, passwordService, jwtService);
    }
}
