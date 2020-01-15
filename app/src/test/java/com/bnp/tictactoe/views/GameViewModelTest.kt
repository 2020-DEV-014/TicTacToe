package com.bnp.tictactoe.views

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bnp.tictactoe.model.Player
import org.junit.Assert.assertNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class GameViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel : GameViewModel

    @Before
    fun initialiseViewModel() {
        viewModel = GameViewModel()
    }

    @Test
    fun getPlayerForPositionAtStartup() {
        assertEquals(Player.NONE, viewModel.liveDataBoard[0][0].getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.liveDataBoard[0][1].getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.liveDataBoard[0][2].getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.liveDataBoard[1][0].getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.liveDataBoard[1][1].getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.liveDataBoard[1][2].getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.liveDataBoard[2][0].getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.liveDataBoard[2][1].getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.liveDataBoard[2][2].getOrAwaitValue())
    }

    @Test
    fun getPlayerPlayingAtStartup() {
        assertEquals(Player.X, viewModel.currentPlayer.getOrAwaitValue())
    }

    @Test
    fun isPlayedPositionUpdated() {
        viewModel.playAtPosition(0, 0)
        assertEquals(Player.X, viewModel.liveDataBoard[0][0].getOrAwaitValue())
    }

    @Test
    fun isPlayerUpdated() {
        viewModel.playAtPosition(0, 0)
        assertEquals(Player.O, viewModel.currentPlayer.getOrAwaitValue())
    }

    @Test
    fun isUserWarnedOfWrongPosition() {
        generatePlayingError()
        assertNotNull(viewModel.error.getOrAwaitValue())
    }

    @Test
    fun isErrorReset() {
        generatePlayingError()
        viewModel.errorReceived()
        assertNull(viewModel.error.getOrAwaitValue())
    }

    private fun generatePlayingError() {
        viewModel.playAtPosition(0, 0)
        viewModel.playAtPosition(0, 0)
    }
}