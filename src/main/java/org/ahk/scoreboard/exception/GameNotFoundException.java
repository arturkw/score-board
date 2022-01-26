package org.ahk.scoreboard.exception;

public class GameNotFoundException extends RuntimeException implements GameUpdateException {

    private static final String GAME_NOT_FOUND = "Game not found. [homeTeamName, awayTeamName] = [%s, %s]";

    public GameNotFoundException(final String homeTeamName, final String awayTeamName) {
        super(String.format(GAME_NOT_FOUND, homeTeamName, awayTeamName));
    }

}
