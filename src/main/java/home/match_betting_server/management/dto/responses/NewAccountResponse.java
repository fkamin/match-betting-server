package home.match_betting_server.management.dto.responses;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NewAccountResponse {
    public String login;
    public String plainPassword;
}
