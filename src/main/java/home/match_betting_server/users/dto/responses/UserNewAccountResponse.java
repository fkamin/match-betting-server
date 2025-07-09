package home.match_betting_server.users.dto.responses;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserNewAccountResponse {
    private Long id;
    private String name;
    private String login;
    private String password;
}
