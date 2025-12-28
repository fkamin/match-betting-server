package home.match_betting_server.teams.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import home.match_betting_server.teams.dto.responses.GroupDetailedResponse;
import home.match_betting_server.teams.dto.responses.GroupSimplifiedResponse;
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
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Team> teams = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    public GroupSimplifiedResponse toSimplifiedResponse() {
        return new GroupSimplifiedResponse(id, name);
    }

    public GroupDetailedResponse toDetailedResponse() {
        return new GroupDetailedResponse(id, name, teams);
    }
}
