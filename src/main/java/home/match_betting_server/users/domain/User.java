package home.match_betting_server.users.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import home.match_betting_server.phase_user_stats.domain.PhaseUserStats;
import home.match_betting_server.users.dto.responses.UserDetailedResponse;
import home.match_betting_server.users.dto.responses.UserNewAccountResponse;
import home.match_betting_server.users.dto.responses.UserSimplifiedResponse;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<PhaseUserStats> phaseUserStats = new ArrayList<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserSimplifiedResponse toSimplifiedResponse() {
        return new UserSimplifiedResponse(id, name);
    }

    public UserDetailedResponse toDetailedResponse() {
        return new UserDetailedResponse(id, login, name, role, phaseUserStats);
    }

    public UserNewAccountResponse toNewAccountResponse(Long userId) {
        return new UserNewAccountResponse(userId, name, login, password);
    }
}
