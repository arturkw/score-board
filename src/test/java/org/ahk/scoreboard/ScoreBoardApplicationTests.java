package org.ahk.scoreboard;

import org.ahk.scoreboard.exception.GameNotFoundException;
import org.ahk.scoreboard.exception.IncorrectScoreValuesException;
import org.ahk.scoreboard.exception.NegativeScoreValuesException;
import org.ahk.scoreboard.exception.WrongOpponentsOrderException;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardApplicationTests {

    @Test
    public void start_game_test() {
        //given:
        HashMapScoreBoard scoreBoard = new HashMapScoreBoard();

        //when:
        scoreBoard.startGame("A", "B");

        //then:
        List<Game> games = scoreBoard.gamesByStartDateDesc();
        assertEquals(1, games.size());
        Game game = games.get(0);
        assertEquals(0, game.gameScore().getAwayTeamScore());
        assertEquals(0, game.gameScore().getHomeTeamScore());
        assertEquals("A", game.getHomeTeamName());
        assertEquals("B", game.getAwayTeamName());
        assertTrue(ZonedDateTime.now().isAfter(game.createdAt()));
    }

    @Test
    public void throw_exception_when_opponents_in_wrong_order() {
        //given:
        HashMapScoreBoard scoreBoard = new HashMapScoreBoard();

        //when:
        Game game = scoreBoard.startGame("A", "B");

        //then:
        assertThrows(WrongOpponentsOrderException.class, () -> scoreBoard.startGame("B", "A"));
        assertEquals(1, scoreBoard.gamesByStartDateDesc().size());
        assertEquals(game.createdAt(), scoreBoard.gamesByStartDateDesc().get(0).createdAt());
    }

    @Test
    public void finish_game_test() {
        //given:
        HashMapScoreBoard scoreBoard = new HashMapScoreBoard();
        Game game = scoreBoard.startGame("A", "B");
        scoreBoard.startGame("C", "D");

        //when:
        Game finishedGame = scoreBoard.finishGame("C", "D");

        //then:
        List<Game> games = scoreBoard.gamesByStartDateDesc();
        assertEquals(1, games.size());
        Game gameLeft = games.get(0);
        assertEquals("A", gameLeft.getHomeTeamName());
        assertEquals("B", gameLeft.getAwayTeamName());
        assertEquals("C", finishedGame.getHomeTeamName());
        assertEquals("D", finishedGame.getAwayTeamName());
    }

    @Test
    public void throw_exception_for_finish_game_when_game_does_not_exist_test() {
        //given:
        HashMapScoreBoard scoreBoard = new HashMapScoreBoard();
        Game game = scoreBoard.startGame("A", "B");

        //then:
        List<Game> games = scoreBoard.gamesByStartDateDesc();
        assertEquals(1, games.size());
        Game gameLeft = games.get(0);
        assertEquals("A", gameLeft.getHomeTeamName());
        assertEquals("B", gameLeft.getAwayTeamName());
        assertThrows(GameNotFoundException.class, () -> scoreBoard.finishGame("C", "D"));
    }

    @Test
    public void throw_exception_when_update_score_for_fantom_game() {
        //given:
        HashMapScoreBoard scoreBoard = new HashMapScoreBoard();
        Game game = scoreBoard.startGame("A", "B");

        //then:
        assertThrows(GameNotFoundException.class, () -> scoreBoard.updateScore("C", "D", 1, 1));
    }

    @Test
    public void update_score_for_existing_game_test() {
        //given:
        HashMapScoreBoard scoreBoard = new HashMapScoreBoard();
        Game game = scoreBoard.startGame("A", "B");

        //when:
        Game updatedGame = scoreBoard.updateScore("A", "B", 1, 0);

        //then:
        assertNotNull(updatedGame);
        assertEquals("A", updatedGame.getHomeTeamName());
        assertEquals("B", updatedGame.getAwayTeamName());
        assertEquals(1, updatedGame.gameScore().getHomeTeamScore());
        assertEquals(0, updatedGame.gameScore().getAwayTeamScore());
    }

    @Test
    public void update_failed_when_scores_are_wrong_test() {
        //given:
        HashMapScoreBoard scoreBoard = new HashMapScoreBoard();
        Game game = scoreBoard.startGame("A", "B");

        //then:
        assertThrows(IncorrectScoreValuesException.class, () -> scoreBoard.updateScore("A", "B", 1, 1));
    }

    @Test
    public void throw_exception_for_incorrect_scores() {
        //given:
        HashMapScoreBoard scoreBoard = new HashMapScoreBoard();
        Game game = scoreBoard.startGame("A", "B");

        //then:
        assertThrows(IncorrectScoreValuesException.class, () -> scoreBoard.updateScore("A", "B", 2, 0));
    }

    @Test
    public void throw_exception_when_updated_score_is_negative() {
        //given:
        HashMapScoreBoard scoreBoard = new HashMapScoreBoard();
        Game game = scoreBoard.startGame("A", "B");

        //then:
        assertThrows(NegativeScoreValuesException.class, () -> scoreBoard.updateScore("A", "B", -1, 0));
    }

    @Test
    public void test_result_rollback() {
        //given:
        HashMapScoreBoard scoreBoard = new HashMapScoreBoard();
        Game game = scoreBoard.startGame("A", "B");
        scoreBoard.updateScore("A", "B", 1, 0);

        //when:
        scoreBoard.updateScore("A", "B", 0, 0);

        //then:
        List<Game> games = scoreBoard.gamesByStartDateDesc();
        assertNotNull(games.get(0));
        assertEquals(0, games.get(0).gameScore().getHomeTeamScore());
        assertEquals(0, games.get(0).gameScore().getAwayTeamScore());
    }

    @Test
    public void test_if_summary_sorted_by_game_date_desc() {
        //given:
        HashMapScoreBoard scoreBoard = new HashMapScoreBoard();
        Game game0 = scoreBoard.startGame("A", "B");
        Game game1 = scoreBoard.startGame("C", "D");
        Game game2 = scoreBoard.startGame("E", "F");

        //when:
        List<Game> games = scoreBoard.gamesByStartDateDesc();

        //then:
        assertEquals(3, games.size());
        assertFalse(game0.createdAt().isAfter(game1.createdAt()));
        assertFalse(game1.createdAt().isAfter(game2.createdAt()));
    }
}