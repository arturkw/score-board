package org.ahk.scoreboard.exception;

public class IncorrectScoreValuesException extends RuntimeException implements GameUpdateException {

    private static final String TOO_BIG_SCORE_CHANGE_EXCEPTION_MESSAGE = "Match result can not be changed by more than one goal at once";

    public IncorrectScoreValuesException() {
        super(TOO_BIG_SCORE_CHANGE_EXCEPTION_MESSAGE);
    }

}
