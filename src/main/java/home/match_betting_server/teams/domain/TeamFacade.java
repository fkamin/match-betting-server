package home.match_betting_server.teams.domain;

import home.match_betting_server.teams.dto.exceptions.*;
import home.match_betting_server.teams.dto.requests.CreateGroupRequest;
import home.match_betting_server.teams.dto.requests.CreateTeamRequest;
import home.match_betting_server.teams.dto.requests.UpdateGroupNameRequest;
import home.match_betting_server.teams.dto.requests.UpdateTeamRequest;
import home.match_betting_server.teams.dto.responses.GroupDetailedResponse;
import home.match_betting_server.teams.dto.responses.GroupSimplifiedResponse;
import home.match_betting_server.teams.dto.responses.TeamDetailedResponse;
import home.match_betting_server.teams.dto.responses.TeamSimplifiedResponse;

import java.util.List;

public class TeamFacade {
    private final TeamRepository teamRepository;
    private final GroupRepository groupRepository;

    public TeamFacade(TeamRepository teamRepository, GroupRepository groupRepository) {
        this.teamRepository = teamRepository;
        this.groupRepository = groupRepository;
    }

    public GroupSimplifiedResponse createGroup(CreateGroupRequest createGroupRequest) {
        validateGroupCreationOrUpdateConditions(createGroupRequest.getName());

        Group newGroup = new Group(createGroupRequest.getName());

        return groupRepository.save(newGroup).toSimplifiedResponse();
    }

    public List<GroupSimplifiedResponse> getAllGroups() {
        return groupRepository.findAll().stream().map(Group::toSimplifiedResponse).toList();
    }

    public GroupDetailedResponse getGroupDetails(Long groupId) {
        return findGroupById(groupId).toDetailedResponse();
    }

    public GroupSimplifiedResponse updateGroupName(Long groupId, UpdateGroupNameRequest updateGroupNameRequest) {
        Group groupToUpdate = findGroupById(groupId);

        validateGroupCreationOrUpdateConditions(updateGroupNameRequest.getName());
        groupToUpdate.setName(updateGroupNameRequest.getName());

        return groupRepository.save(groupToUpdate).toSimplifiedResponse();
    }

    public void deleteGroup(Long groupId) {
        groupRepository.delete(findGroupById(groupId));
    }

    public TeamDetailedResponse createTeam(Long groupId, CreateTeamRequest createTeamRequest) {
        Group group = findGroupById(groupId);

        validateTeamNameConditions(createTeamRequest.getName());
        Team newTeam = new Team(createTeamRequest.getName(), group);
        group.getTeams().add(newTeam);

        return teamRepository.save(newTeam).toDetailedResponse();
    }

    public List<TeamSimplifiedResponse> getAllTeams(Long groupId) {
        Group group = findGroupById(groupId);

        return group.getTeams().stream().map(Team::toSimplifiedResponse).toList();
    }

    public TeamDetailedResponse getTeamDetails(Long groupId, Long teamId) {
        findGroupById(groupId);
        return findTeamById(teamId).toDetailedResponse();
    }

    public TeamDetailedResponse updateTeam(Long groupId, Long teamId, UpdateTeamRequest updateTeamRequest) {
        Group newGroup = findGroupById(groupId);
        Team team = findTeamById(teamId);

        validateTeamName(updateTeamRequest.getName());

        team.setName(updateTeamRequest.getName());
        team.setGroup(newGroup);

        return teamRepository.save(team).toDetailedResponse();
    }

    public void deleteTeam(Long groupId, Long teamId) {
        findGroupById(groupId);
        teamRepository.delete(findTeamById(teamId));
    }

    private void validateGroupCreationOrUpdateConditions(String groupName) {
        validateGroupName(groupName);
        validateGroupExistence(groupName);
    }

    private void validateGroupName(String name) {
        if (name == null || name.isEmpty()) throw new GroupNameCanNotBeNullOrEmptyException();
    }

    private void validateGroupExistence(String name) {
        if (groupRepository.existsByName(name)) throw new GroupWithThatNameAlreadyExistsException();
    }

    private Group findGroupById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(GroupDoesNotExistsException::new);
    }

    private void validateTeamNameConditions(String name) {
        validateTeamName(name);
        validateTeamExistence(name);
    }

    private void validateTeamName(String name) {
        if (teamRepository.existsByName(name) && name != null) throw new TeamNameCanNotBeNullOrEmptyException();
    }

    private void validateTeamExistence(String name) {
        if (teamRepository.existsByName(name)) throw new TeamWithThatNameAlreadyExistsException();
    }

    private Team findTeamById(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(TeamDoesNotExistsException::new);
    }
}
