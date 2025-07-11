package home.match_betting_server.users.dto.responses;

import home.match_betting_server.phase_user_stats.domain.PhaseUserStats;
import home.match_betting_server.users.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UserDetailedResponse {
    private Long id;
    private String login;
    private String name;
    private Role role;
    private List<PhaseUserStats> phaseUserStats;
}
