package home.match_betting_server.teams.domain;

import home.match_betting_server.teams.dto.exceptions.GroupNotFoundException;
import home.match_betting_server.teams.dto.exceptions.GroupWithThatNameAlreadyExistsException;
import home.match_betting_server.teams.dto.exceptions.TeamNotFoundException;
import home.match_betting_server.teams.dto.exceptions.TeamWithThatNameAlreadyExistsException;
import home.match_betting_server.teams.dto.requests.CreateGroupRequest;
import home.match_betting_server.teams.dto.requests.CreateTeamRequest;
import home.match_betting_server.teams.dto.requests.UpdateGroupNameRequest;
import home.match_betting_server.teams.dto.requests.UpdateTeamRequest;
import home.match_betting_server.teams.dto.responses.GroupDetailedResponse;
import home.match_betting_server.teams.dto.responses.GroupSimplifiedResponse;
import home.match_betting_server.teams.dto.responses.TeamDetailedResponse;
import home.match_betting_server.teams.dto.responses.TeamSimplifiedResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TeamFacade {
    private final TeamRepository teamRepository;
    private final GroupRepository groupRepository;

    public TeamFacade(TeamRepository teamRepository, GroupRepository groupRepository) {
        this.teamRepository = teamRepository;
        this.groupRepository = groupRepository;
    }

    public GroupSimplifiedResponse createGroup(CreateGroupRequest createGroupRequest) {
        if (groupRepository.existsByName(createGroupRequest.getName()))
            throw new GroupWithThatNameAlreadyExistsException();

        return groupRepository.save(new Group(createGroupRequest.getName())).toSimplifiedResponse();
    }

    public List<GroupSimplifiedResponse> getAllGroups() {
        return groupRepository.findAll().stream().map(Group::toSimplifiedResponse).toList();
    }

    public GroupDetailedResponse getGroup(Long groupId) {
        return findGroupById(groupId).toDetailedResponse();
    }

    public GroupDetailedResponse updateGroupName(UpdateGroupNameRequest updateGroupNameRequest, Long groupId) {
        Group group = findGroupById(groupId);
        group.setName(updateGroupNameRequest.getName());
        return groupRepository.save(group).toDetailedResponse();
    }

    public ResponseEntity<String> deleteGroup(Long groupId) {
        Group group = findGroupById(groupId);
        groupRepository.delete(group);
        return ResponseEntity.ok("Deleted group with id " + groupId);
    }

    public TeamDetailedResponse createTeam(CreateTeamRequest createTeamRequest, Long groupId) {
        if (teamRepository.existsByName(createTeamRequest.getName()) && createTeamRequest.getName() != null)
            throw new TeamWithThatNameAlreadyExistsException();

        Group group = findGroupById(groupId);
        Team newTeam = new Team(createTeamRequest.getName(), group);

        group.getTeams().add(newTeam);

        return teamRepository.save(newTeam).toDetailedResponse(groupId);
    }

    public List<TeamSimplifiedResponse> getAllTeams(Long groupId) {
        Group group = findGroupById(groupId);

        return group.getTeams().stream().map(Team::toSimplifiedResponse).toList();
    }

    public TeamDetailedResponse getTeam(Long groupId, Long teamId) {
        return findTeamById(teamId).toDetailedResponse(groupId);
    }

    public TeamDetailedResponse updateTeam(UpdateTeamRequest updateTeamRequest, Long groupId, Long teamId) {
        Group newGroup = findGroupById(updateTeamRequest.getGroupId());
        Team team = findTeamById(teamId);

        team.setName(updateTeamRequest.getName());
        team.setGroup(newGroup);

        return teamRepository.save(team).toDetailedResponse(groupId);
    }

    public ResponseEntity<String> deleteTeam(Long groupId, Long teamId) {
        Team team = findTeamById(teamId);
        teamRepository.delete(team);
        return ResponseEntity.ok("Deleted team with id " + teamId + " from group with id " + groupId);
    }

    private Group findGroupById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
    }

    private Team findTeamById(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(TeamNotFoundException::new);
    }
}
