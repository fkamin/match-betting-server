package home.match_betting_server.users.dto.responses;

import home.match_betting_server.users.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDetailedResponse {
    private Long id;
    private String login;
    private String name;
    private Role role;
}
