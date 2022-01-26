package org.ahk.scoreboard.exception;

public class WrongOpponentsOrderException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Wrong opponents order: %s, %s. Game with opposite opponent order registered already.";

    public WrongOpponentsOrderException(String homeTeamName, String awayTeamName) {
        super(String.format(EXCEPTION_MESSAGE, homeTeamName, awayTeamName));
    }

}
