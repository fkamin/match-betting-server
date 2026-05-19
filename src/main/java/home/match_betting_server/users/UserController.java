package home.match_betting_server.users;

import home.match_betting_server.users.domain.User;
import home.match_betting_server.users.domain.UserFacade;
import home.match_betting_server.users.dto.requests.LoginRequest;
import home.match_betting_server.users.dto.requests.RegisterRequest;
import home.match_betting_server.users.dto.responses.AuthResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "authorization")
public class UserController {
    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping("/register")
    public AuthResponse createUser(@RequestBody RegisterRequest registerRequest) {
        return userFacade.register(registerRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        return userFacade.login(loginRequest);
    }

    @GetMapping("/testEndpoint")
    public String testEndpoint() {
        return "Test Endpoint - public";
    }

    @GetMapping("/me")
    public String me(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return "Logged in user: " + user.getLogin();
    }
}
