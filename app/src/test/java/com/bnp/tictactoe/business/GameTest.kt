package com.bnp.tictactoe.business

import com.bnp.tictactoe.model.Player
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.lang.IllegalStateException

class GameTest {

    private lateinit var game: Game

    @Before
    fun initTestGame() {
        game = Game()
    }

    @Test
    fun getPlayerAtPositionAtStartupReturnPlayerNone() {
        for (line in 0 until Game.BOARD_SIZE) {
            for (column in 0 until Game.BOARD_SIZE) {
                assertFalse(game.getPlayerAtPosition(line, column).isPlayer)
            }
        }
    }

    @Test
    fun getPlayerOutsideBoardThrowException() {
        try {
            game.getPlayerAtPosition(0, Game.BOARD_SIZE)
            fail("The getPlayerAtPosition should have failed because the column was too high")
        } catch (exception: Exception) {
            assertTrue(exception is IllegalArgumentException)
            assertFalse(exception is IllegalPlayingPositionException)
        }
        try {
            game.getPlayerAtPosition(0, -1)
            fail("The getPlayerAtPosition should have failed because the column was too low")
        } catch (exception: Exception) {
            assertTrue(exception is IllegalArgumentException)
            assertFalse(exception is IllegalPlayingPositionException)
        }
        try {
            game.getPlayerAtPosition(Game.BOARD_SIZE, 0)
            fail("The getPlayerAtPosition should have failed because the line was too high")
        } catch (exception: Exception) {
            assertTrue(exception is IllegalArgumentException)
            assertFalse(exception is IllegalPlayingPositionException)
        }
        try {
            game.getPlayerAtPosition(-1, 0)
            fail("The getPlayerAtPosition should have failed because the line was too low")
        } catch (exception: Exception) {
            assertTrue(exception is IllegalArgumentException)
            assertFalse(exception is IllegalPlayingPositionException)
        }
    }

    @Test
    fun getFirstPlayer() {
        assertEquals(Player.X, game.getCurrentPlayer())
    }

    @Test
    fun isSquareChangedAfterPlay() {
        game.playAtPosition(0, 0)
        assertEquals(Player.X, game.getPlayerAtPosition(0, 0))
        for (line in 0 until Game.BOARD_SIZE) {
            for (column in 0 until Game.BOARD_SIZE) {
                if (line == 0 && column == 0) {
                    continue
                }
                assertFalse(game.getPlayerAtPosition(line, column).isPlayer)
            }
        }
    }

    @Test
    fun isPlayerChangedAfterPlay() {
        game.playAtPosition(0, 0)
        assertEquals(Player.O, game.getCurrentPlayer())
        game.playAtPosition(0, 1)
        assertEquals(Player.X, game.getCurrentPlayer())

    }

    @Test
    fun cantPlayOnOccupiedSquare() {
        game.playAtPosition(0, 0)
        try {
            game.playAtPosition(0, 0)
            fail("The player shouldn't be able to play this square")
        } catch (exception: Exception) {
            assertTrue(exception is IllegalPlayingPositionException)
        }
    }

    @Test
    fun checkHorizontalVictory() {
        checkOneLineVictory(0, 1)
        game = Game()
        checkOneLineVictory(1, 2)
        game = Game()
        checkOneLineVictory(2, 0)
    }

    private fun checkOneLineVictory(winningLine: Int, losingLine: Int) {
        assertNotEquals(winningLine, losingLine)
        game.playAtPosition(winningLine, 0)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(losingLine, 0)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(winningLine, 1)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(losingLine, 1)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(winningLine, 2)
        assertEquals(Player.X, game.winner)
    }

    @Test
    fun checkVerticalVictory() {
        checkOneColumnVictory(0, 1)
        game = Game()
        checkOneColumnVictory(1, 2)
        game = Game()
        checkOneColumnVictory(2, 0)
    }

    private fun checkOneColumnVictory(winningColumn: Int, losingColumn: Int) {
        assertNotEquals(winningColumn, losingColumn)
        game.playAtPosition(0, winningColumn)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(0, losingColumn)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(1, winningColumn)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(1, losingColumn)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(2, winningColumn)
        assertEquals(Player.X, game.winner)
    }

    @Test
    fun checkDownwardVictory() {
        game.playAtPosition(0, 0)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(1, 0)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(1, 1)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(1, 2)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(2, 2)
        assertEquals(Player.X, game.winner)

    }

    @Test
    fun checkUpwardVictory() {
        game.playAtPosition(2, 0)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(1, 0)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(1, 1)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(1, 2)
        assertFalse(game.winner.isPlayer)
        game.playAtPosition(0, 2)
        assertEquals(Player.X, game.winner)
    }

    @Test
    fun cantPlayAfterWinning() {
        checkOneLineVictory(1,0)
        try {
            game.playAtPosition(2, 0)
            fail("The game should block after a victory")
        } catch (exception: Exception) {
            assertTrue(exception is IllegalStateException)
        }
    }

    @Test
    fun isFinishedAfterADraw() {
        game.playAtPosition(0,0)
        game.playAtPosition(0,1)
        game.playAtPosition(0,2)
        game.playAtPosition(1,1)
        game.playAtPosition(1,0)
        game.playAtPosition(1,2)
        game.playAtPosition(2,2)
        game.playAtPosition(2,0)
        game.playAtPosition(2,1)
        assertFalse(game.winner.isPlayer)
        assertTrue(game.isFinished())
    }

    @Test
    fun isFinishedAfterWin() {
        checkOneLineVictory(0, 1)
        assertTrue(game.isFinished())
    }
}