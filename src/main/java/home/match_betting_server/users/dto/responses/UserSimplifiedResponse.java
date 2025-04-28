package home.match_betting_server.users.dto.responses;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserSimplifiedResponse {
    public Long id;
    public String login;
    public String password;
}
