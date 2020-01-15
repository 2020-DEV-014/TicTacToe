package com.bnp.tictactoe.views

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bnp.tictactoe.model.Player
import org.junit.Assert.assertEquals
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
        assertEquals(Player.NONE, viewModel.zeroZero.getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.zeroOne.getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.zeroTwo.getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.oneZero.getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.oneOne.getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.oneTwo.getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.twoZero.getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.twoOne.getOrAwaitValue())
        assertEquals(Player.NONE, viewModel.twoTwo.getOrAwaitValue())
    }

    @Test
    fun getPlayerPlayingAtStartup() {
        assertEquals(Player.X, viewModel.currentPlayer.getOrAwaitValue())
    }
}