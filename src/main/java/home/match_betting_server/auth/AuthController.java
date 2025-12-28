package home.match_betting_server.auth;

import home.match_betting_server.auth.domain.AuthFacade;
import home.match_betting_server.auth.dto.requests.LoginRequest;
import home.match_betting_server.auth.dto.responses.JwtResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1_1/auths")
public class AuthController {
    private final AuthFacade authFacade;

    public AuthController(AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid final LoginRequest loginRequest) {
        JwtResponse jwtResponse = authFacade.login(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        authFacade.logout(request);
        return ResponseEntity.noContent().build();
    }
}
