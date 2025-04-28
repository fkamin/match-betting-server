package home.match_betting_server.users.domain;

import home.match_betting_server.management.dto.responses.UserGeneralResponse;
import home.match_betting_server.users.dto.responses.UserSimplifiedResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    private Integer points = 0;

    private Integer betsWithMaxScore = 0;

    private Integer percentageOfCorrectGuesses = 0;

    private Integer rankingPosition = 0;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserSimplifiedResponse toSimplifiedResponse() {
        return new UserSimplifiedResponse(login, password);
    }

    public UserGeneralResponse toGeneralResponse() {
        return new UserGeneralResponse(login, name);
    }
}
