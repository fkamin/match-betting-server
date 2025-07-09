package home.match_betting_server.users.domain;

import home.match_betting_server.users.dto.responses.UserDetailedResponse;
import home.match_betting_server.users.dto.responses.UserNewAccountResponse;
import home.match_betting_server.users.dto.responses.UserSimplifiedResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private int points = 0;
    private int betsWithMaxScore = 0;
    private double percentageOfCorrectGuesses = 0;
    private int rankingPosition = 0;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserSimplifiedResponse toSimplifiedResponse() {
        return new UserSimplifiedResponse(id, name, role.getName(), points);
    }

    public UserDetailedResponse toDetailedResponse() {
        return new UserDetailedResponse(id, name, points, betsWithMaxScore, percentageOfCorrectGuesses, rankingPosition);
    }

    public UserNewAccountResponse toNewAccountResponse(Long userId) {
        return new UserNewAccountResponse(userId, name, login, password);
    }

}
