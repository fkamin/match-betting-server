package home.match_betting_server.auth.dto.requests;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(@NotNull String login, @NotNull String password) {}