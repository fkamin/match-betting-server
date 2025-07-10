package home.match_betting_server.users.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserSimplifiedResponse {
    private Long id;
    private String name;
}
