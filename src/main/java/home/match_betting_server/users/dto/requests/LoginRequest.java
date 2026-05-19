package home.match_betting_server.users.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank
        String login,
        @NotBlank
        String password) {
}
