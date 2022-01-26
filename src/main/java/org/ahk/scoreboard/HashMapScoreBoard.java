package org.ahk.scoreboard;

import org.ahk.scoreboard.exception.GameNotFoundException;
import org.ahk.scoreboard.exception.IncorrectScoreValuesException;
import org.ahk.scoreboard.exception.NegativeScoreValuesException;
import org.ahk.scoreboard.exception.WrongOpponentsOrderException;

import java.util.*;

/**
 * Scoreboard backed by HashMap.
 * This implementation is not thread safe.
 */
final public class HashMapScoreBoard implements ScoreBoard {

    private final Map<Key, Game> scoreBoard = new HashMap<>();

    @Override
    public Game startGame(final String homeTeamName, final String awayTeamName) {
        Key key = new Key(homeTeamName.toUpperCase(), awayTeamName.toUpperCase());
        if (scoreBoard.containsKey(new Key(awayTeamName, homeTeamName))) {
            throw new WrongOpponentsOrderException(homeTeamName, awayTeamName);
        }
        return scoreBoard.computeIfAbsent(key, Game::new);
    }

    @Override
    public Game finishGame(final String homeTeamName, final String awayTeamName) {
        Game removed = scoreBoard.remove(new Key(homeTeamName, awayTeamName));
        if (removed != null) {
            return removed;
        }
        throw new GameNotFoundException(homeTeamName, awayTeamName);
    }

    @Override
    public Game updateScore(final String homeTeamName, final String awayTeamName, final int homeTeamScore, final int awayTeamScore) {
        Key key = new Key(homeTeamName, awayTeamName);
        Game game = scoreBoard.get(key);
        if (game != null) {
            GameScore gameScore = game.gameScore();
            validateInput(homeTeamScore, awayTeamScore, gameScore);
            return scoreBoard.computeIfPresent(key, (k, e) -> new Game(key, homeTeamScore, awayTeamScore, e.createdAt()));
        }
        throw new GameNotFoundException(homeTeamName, awayTeamName);
    }

    private void validateInput(int homeTeamScore, int awayTeamScore, GameScore gameScore) {
        if (Math.abs(gameScore.getAwayTeamScore() + gameScore.getHomeTeamScore() - homeTeamScore - awayTeamScore) > 1) {
            throw new IncorrectScoreValuesException();
        }
        if (gameScore.getHomeTeamScore() + homeTeamScore < 0 || gameScore.getAwayTeamScore() + awayTeamScore < 0) {
            throw new NegativeScoreValuesException();
        }
    }

    @Override
    public List<Game> gamesByStartDateDesc() {
        ArrayList<Game> games = new ArrayList<>(scoreBoard.values());
        games.sort(Comparator.comparing(Game::createdAt));
        return games;
    }
}
