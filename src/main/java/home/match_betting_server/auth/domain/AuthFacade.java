package home.match_betting_server.auth.domain;

import home.match_betting_server.auth.dto.exceptions.InvalidLoginOrPasswordException;
import home.match_betting_server.auth.dto.requests.LoginRequest;
import home.match_betting_server.auth.dto.responses.JwtResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class AuthFacade {
    private final UserDetailsService userDetailsService;
    private final PasswordService passwordService;
    private final JwtService jwtService;

    public AuthFacade(UserDetailsService userDetailsService, PasswordService passwordService, JwtService jwtService) {
        this.userDetailsService = userDetailsService;
        this.passwordService = passwordService;
        this.jwtService = jwtService;
    }

    public JwtResponse login(LoginRequest loginRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.login());

        if (passwordService.checkPassword(loginRequest.password(), userDetails.getPassword())) {
            throw new InvalidLoginOrPasswordException();
        }

        String token = jwtService.generateJwtToken(userDetails).getTokenValue();
        return new JwtResponse(token);
    }

    public void logout(HttpServletRequest request) {
        String token = jwtService.extractJwtToken(request);

        if (token != null && jwtService.validateToken(token)) {

        }
    }
}
