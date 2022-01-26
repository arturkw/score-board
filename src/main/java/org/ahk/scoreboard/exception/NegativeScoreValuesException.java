package org.ahk.scoreboard.exception;

public class NegativeScoreValuesException extends RuntimeException implements GameUpdateException {

    private static final String NEGATIVE_SCORE_EXCEPTION_MESSAGE = "Scored result can not be negative.";

    public NegativeScoreValuesException() {
        super(NEGATIVE_SCORE_EXCEPTION_MESSAGE);
    }
}
