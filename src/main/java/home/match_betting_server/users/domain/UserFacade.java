package home.match_betting_server.users.domain;

import home.match_betting_server.auth.JwtService;
import home.match_betting_server.users.dto.exceptions.InvalidCredentialsException;
import home.match_betting_server.users.dto.exceptions.UserAlreadyExistsException;
import home.match_betting_server.users.dto.requests.LoginRequest;
import home.match_betting_server.users.dto.requests.RegisterRequest;
import home.match_betting_server.users.dto.responses.AuthResponse;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

public class UserFacade {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserFacade(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByLogin(registerRequest.login())) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();

        user.setLogin(registerRequest.login());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setRole(UserRole.USER);
        user.setEnabled(true);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return new AuthResponse(jwtService.generateToken(user));
    }

    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByLogin(loginRequest.login())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return new AuthResponse(jwtService.generateToken(user));
    }
}
