package org.ahk.scoreboard;

import java.time.ZonedDateTime;

public final class Game {
    private final GameScore score;
    private final ZonedDateTime createdAt;
    private final Key key;

    Game(final Key key, final int homeScored, final int awayScored, ZonedDateTime createdAt) {
        this.score = new GameScore(homeScored, awayScored);
        this.createdAt = createdAt;
        this.key = key;
    }

    Game(Key key) {
        this.key = key;
        this.score = new GameScore(0, 0);
        this.createdAt = ZonedDateTime.now();
    }

    public GameScore gameScore() {
        return score;
    }

    public ZonedDateTime createdAt() {
        return createdAt;
    }

    public String getHomeTeamName() {
        return key.getHomeTeamName();
    }

    public String getAwayTeamName() {
        return key.getAwayTeamName();
    }
}

