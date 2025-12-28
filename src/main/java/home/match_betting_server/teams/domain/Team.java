package home.match_betting_server.teams.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import home.match_betting_server.teams.dto.responses.TeamDetailedResponse;
import home.match_betting_server.teams.dto.responses.TeamSimplifiedResponse;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "teams")
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonBackReference
    private Group group;

    public Team(String name, Group group) {
        this.name = name;
        this.group = group;
    }

    public TeamDetailedResponse toDetailedResponse() {
        return new TeamDetailedResponse(id, name, group.getId());
    }

    public TeamSimplifiedResponse toSimplifiedResponse() {
        return new TeamSimplifiedResponse(id, name);
    }
}
