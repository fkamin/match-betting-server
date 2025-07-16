package home.match_betting_server.users.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserNewAccountResponse {
    private Long id;
    private String name;
    private String login;
    private String password;
}
