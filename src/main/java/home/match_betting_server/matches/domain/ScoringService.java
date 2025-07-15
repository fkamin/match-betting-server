package home.match_betting_server.matches.domain;

import home.match_betting_server.bets.domain.Bet;
import home.match_betting_server.bets.domain.BetRepository;
import home.match_betting_server.phase_user_stats.domain.PhaseUserStats;
import home.match_betting_server.phase_user_stats.domain.PhaseUserStatsRepository;
import home.match_betting_server.phase_user_stats.dto.exceptions.PhaseUserStatsDoesNotExistsException;
import home.match_betting_server.phases.domain.Phase;
import home.match_betting_server.phases.domain.PhaseType;
import home.match_betting_server.users.domain.User;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class ScoringService {
    private final BetRepository betRepository;
    private final PhaseUserStatsRepository phaseUserStatsRepository;

    public ScoringService(BetRepository betRepository, PhaseUserStatsRepository phaseUserStatsRepository) {
        this.betRepository = betRepository;
        this.phaseUserStatsRepository = phaseUserStatsRepository;
    }

    public void assignPointsForMatch(Match match) {
        List<Bet> bets = betRepository.findAllByMatch(match);

        for (Bet bet : bets) {
            IntBooleanResult result = calculatePoints(match, bet);
            bet.setPointsForBet(result.points);
            bet.setMaxPointsIn90Minutes(result.maxPointsIn90Minutes);
            betRepository.save(bet);

            PhaseUserStats phaseUserStats = getPhaseUserStats(bet.getMatch().getPhase(), bet.getUser());
            updatePhaseUserStats(phaseUserStats, bet);
        }

        updateRanking(match.getPhase());
    }

    private IntBooleanResult calculatePoints(Match match, Bet bet) {
        int points = 0;
        boolean maxPointsIn90Minutes = false;

        if (match.getTeamLeftScore().equals(bet.getBetLeftScore()) && match.getTeamRightScore().equals(bet.getBetRightScore())) {
            points = 3;
            maxPointsIn90Minutes = true;
        } else if ((match.getTeamLeftScore() > match.getTeamRightScore() && bet.getBetLeftScore() > bet.getBetRightScore())
                        || (match.getTeamLeftScore() < match.getTeamRightScore() && bet.getBetLeftScore() < bet.getBetRightScore())
                        || (match.getTeamLeftScore().equals(match.getTeamRightScore()) && Objects.equals(bet.getBetLeftScore(), bet.getBetRightScore()))
        ) {
            points = 1;
        }

        if (match.getPhase().isKnockoutStage()) {
            if (match.getMatchWinner().equals(bet.getBetWinnerTeam())) {
                points += 2;
            }
        }

        return new IntBooleanResult(points, maxPointsIn90Minutes);
    }

    private PhaseUserStats getPhaseUserStats(Phase phase, User user) {
        return phaseUserStatsRepository.findByPhaseAndUser(phase, user).orElseThrow(PhaseUserStatsDoesNotExistsException::new);
    }

    private void updatePhaseUserStats(PhaseUserStats phaseUserStats, Bet bet) {
        phaseUserStats.setPoints(phaseUserStats.getPoints() + bet.getPointsForBet());

        if (bet.isMaxPointsIn90Minutes()) {
            phaseUserStats.setBetsWithMaxScore(phaseUserStats.getBetsWithMaxScore() + 1);
        }

        double percentageOfCorrectGuesses = calculatePercentageOfCorrectGuesses(phaseUserStats);
        phaseUserStats.setPercentageOfCorrectGuesses(percentageOfCorrectGuesses);

        phaseUserStatsRepository.save(phaseUserStats);
    }

    private double calculatePercentageOfCorrectGuesses(PhaseUserStats phaseUserStats) {
        PhaseType phaseType = phaseUserStats.getPhase().getPhaseType();
        int multiplier = 1;

        if (phaseType == PhaseType.GROUP_STAGE) {
            multiplier = 3;
        } else if (phaseType == PhaseType.KNOCKOUT_STAGE) {
            multiplier = 5;
        }

        int maxPoints = phaseUserStats.getPhase().getMatches().size() * multiplier;

        return (double) phaseUserStats.getPoints() / maxPoints;
    }

    private void updateRanking(Phase phase) {
        List<PhaseUserStats> statsList = phaseUserStatsRepository.findByPhase(phase);

        statsList.sort(Comparator.comparingInt(PhaseUserStats::getPoints).reversed());
        for (int i = 0; i < statsList.size(); i++) {
            PhaseUserStats phaseUserStats = statsList.get(i);
            phaseUserStats.setRankingPosition(i + 1);
            phaseUserStatsRepository.save(phaseUserStats);
        }
    }

    public record IntBooleanResult(int points, boolean maxPointsIn90Minutes) {}
}

