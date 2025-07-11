package home.match_betting_server.matches.domain;

import home.match_betting_server.matches.dto.exceptions.MatchNotFoundException;
import home.match_betting_server.matches.dto.requests.CreateMatchRequest;
import home.match_betting_server.matches.dto.responses.MatchSimplifiedResponse;
import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.phases.domain.PhaseRepository;
import home.match_betting_server.phases.dto.exceptions.PhaseNotFoundException;
import home.match_betting_server.teams.domain.Team;
import home.match_betting_server.teams.domain.TeamRepository;
import home.match_betting_server.teams.dto.exceptions.TeamNotFoundException;

public class MatchFacade {
    private final MatchRepository matchRepository;
    private final PhaseRepository phaseRepository;
    private final TeamRepository teamRepository;

    public MatchFacade(MatchRepository matchRepository, PhaseRepository phaseRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.phaseRepository = phaseRepository;
        this.teamRepository = teamRepository;
    }

    public MatchSimplifiedResponse createMatch(CreateMatchRequest createMatchRequest) {
        Phase phase = findPhaseById(createMatchRequest.getPhaseId());
        Team teamLeft = findTeamById(createMatchRequest.getTeamLeftId());
        Team teamRight = findTeamById(createMatchRequest.getTeamRightId());

        //TODO(DODATKOWA LOGIKA NP. NIE MOZE GRAC FRANCJA VS FRANCJA, NIE MOZE GRAC W FAZIE GRUPOWEJ DRUZYNA Z DRUZYNA Z INNEJ GRUPY)
        //TODO(WALIDACJA DATY MECZU NP. MECZ NIE MOZE BYC 27 LIPCA JEZELI JEST 28 LIPIEC BR.)
        Match match = new Match(phase, teamLeft, teamRight, createMatchRequest.getMatchDate());

        return matchRepository.save(match).toSimplifiedResponse();
    }



    private Match findMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow(MatchNotFoundException::new);
    }

    private Phase findPhaseById(Long id) {
        return phaseRepository.findById(id).orElseThrow(PhaseNotFoundException::new);
    }

    private Team findTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
    }

}
