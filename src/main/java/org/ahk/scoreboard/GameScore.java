package org.ahk.scoreboard;

public final class GameScore {
    private final int homeTeamScore;
    private final int awayTeamScore;

    GameScore(final int homeTeamScore, final int awayTeamScore) {
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }
}
