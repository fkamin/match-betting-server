package home.match_betting_server.users.domain;

import home.match_betting_server.phases.domain.UserPhaseStats;
import home.match_betting_server.users.dto.responses.UserDetailedResponse;
import home.match_betting_server.users.dto.responses.UserNewAccountResponse;
import home.match_betting_server.users.dto.responses.UserSimplifiedResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPhaseStats> userPhaseStats = new ArrayList<>();

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
        return new UserDetailedResponse(id, login, name, role);
    }

    public UserNewAccountResponse toNewAccountResponse(Long userId) {
        return new UserNewAccountResponse(userId, name, login, password);
    }

}
