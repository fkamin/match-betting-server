package home.match_betting_server.users.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // "USER" OR "ADMIN"
    private String description;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
