package com.bnp.tictactoe.business

import com.bnp.tictactoe.model.Player
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

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
        }
        try {
            game.getPlayerAtPosition(0, -1)
            fail("The getPlayerAtPosition should have failed because the column was too low")
        } catch (exception: Exception) {
            assertTrue(exception is IllegalArgumentException)
        }
        try {
            game.getPlayerAtPosition(Game.BOARD_SIZE, 0)
            fail("The getPlayerAtPosition should have failed because the line was too high")
        } catch (exception: Exception) {
            assertTrue(exception is IllegalArgumentException)
        }
        try {
            game.getPlayerAtPosition(-1, 0)
            fail("The getPlayerAtPosition should have failed because the line was too low")
        } catch (exception: Exception) {
            assertTrue(exception is IllegalArgumentException)
        }
    }

    @Test
    fun getFirstPlayer() {
        assertEquals(Player.X, game.getCurrentPlayer())
    }
}