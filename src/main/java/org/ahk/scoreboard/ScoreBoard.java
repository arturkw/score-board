package org.ahk.scoreboard;

import org.ahk.scoreboard.exception.IncorrectScoreValuesException;

import java.util.List;

/**
 * Score Board interface.
 */
public interface ScoreBoard {

    /**
     * Starts a new game.
     * @param homeTeamName home team name
     * @param awayTeamName away team name
     * @return {@link Game} object if game was not registered yet and existing one if game registered already.
     * @throws org.ahk.scoreboard.exception.WrongOpponentsOrderException is thrown when game registered in opposite order
     */
    Game startGame(final String homeTeamName, final String awayTeamName);

    /**
     * Finish game
     * @param homeTeamName home team name
     * @param awayTeamName away team name
     * @return {@link Game} - finished game if found, {@link  org.ahk.scoreboard.exception.GameNotFoundException} when game was not found
     */
    Game finishGame(final String homeTeamName, final String awayTeamName);

    /**
     * Update scored results for a game.
     * It is possible to increase/decrease scores by one (i.e. from 0-0 to 1-0)
     *
     * @param homeTeamName home team name
     * @param awayTeamName away team name
     * @param homeTeamScore new value for home team score
     * @param awayTeamScore new value for away team score
     * @throws IncorrectScoreValuesException when update failed.
     * @return {@link Game} - updated game or throws {@link org.ahk.scoreboard.exception.GameNotFoundException}
     */
    Game updateScore(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore);

    /**
     * Returns list of active games, sorted by creation date.
     * @return list of {@link Game} active games list.
     */
    List<Game> gamesByStartDateDesc();

}
