package home.match_betting_server.teams;

import home.match_betting_server.teams.domain.TeamFacade;
import home.match_betting_server.teams.dto.requests.CreateGroupRequest;
import home.match_betting_server.teams.dto.requests.CreateTeamRequest;
import home.match_betting_server.teams.dto.requests.UpdateGroupNameRequest;
import home.match_betting_server.teams.dto.requests.UpdateTeamRequest;
import home.match_betting_server.teams.dto.responses.GroupDetailedResponse;
import home.match_betting_server.teams.dto.responses.GroupSimplifiedResponse;
import home.match_betting_server.teams.dto.responses.TeamDetailedResponse;
import home.match_betting_server.teams.dto.responses.TeamSimplifiedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_1/groups")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeamController {
    private final TeamFacade teamFacade;

    @PostMapping
    public GroupSimplifiedResponse createGroup(@RequestBody CreateGroupRequest createGroupRequest) {
        return teamFacade.createGroup(createGroupRequest);
    }

    @GetMapping
    public List<GroupSimplifiedResponse> getAllGroups() {
        return teamFacade.getAllGroups();
    }

    @GetMapping("/{groupId}")
    public GroupDetailedResponse getGroupById(@PathVariable Long groupId) {
        return teamFacade.getGroup(groupId);
    }

    @PutMapping("/{groupId}")
    public GroupDetailedResponse updateGroupName(@RequestBody UpdateGroupNameRequest updateGroupNameRequest, @PathVariable Long groupId) {
        return teamFacade.updateGroupName(updateGroupNameRequest, groupId);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long groupId) {
        return teamFacade.deleteGroup(groupId);
    }

    @PostMapping("/{groupId}/teams")
    public TeamDetailedResponse createTeam(@RequestBody CreateTeamRequest createTeamRequest, @PathVariable Long groupId) {
        return teamFacade.createTeam(createTeamRequest, groupId);
    }

    @GetMapping("/{groupId}/teams")
    public List<TeamSimplifiedResponse> getAllTeams(@PathVariable Long groupId) {
        return teamFacade.getAllTeams(groupId);
    }

    @GetMapping("/{groupId}/teams/{teamId}")
    public TeamDetailedResponse getTeam(@PathVariable Long groupId, @PathVariable Long teamId) {
        return teamFacade.getTeam(groupId, teamId);
    }

    @PutMapping("/{groupId}/teams/{teamId}")
    public TeamDetailedResponse updateTeam(@RequestBody UpdateTeamRequest updateTeamRequest, @PathVariable Long groupId, @PathVariable Long teamId) {
        return teamFacade.updateTeam(updateTeamRequest, groupId, teamId);
    }

    @DeleteMapping("/{groupId}/teams/{teamId}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long groupId, @PathVariable Long teamId) {
        return teamFacade.deleteTeam(groupId, teamId);
    }
}
