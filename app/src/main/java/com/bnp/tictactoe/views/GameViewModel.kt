package com.bnp.tictactoe.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bnp.tictactoe.R
import com.bnp.tictactoe.business.Game
import com.bnp.tictactoe.business.IllegalPlayingPositionException
import com.bnp.tictactoe.model.Player

class GameViewModel : ViewModel() {

    private val game = Game()

    private val liveDataBoardMutable = List(Game.BOARD_SIZE) {
        List(Game.BOARD_SIZE) {
            MutableLiveData<Player>()
        }
    }
    val liveDataBoard: List<List<LiveData<Player>>> = liveDataBoardMutable

    private val currentPlayerMutable = MutableLiveData<Player>()
    val currentPlayer: LiveData<Player> = currentPlayerMutable

    private val winningMessageMutable = MutableLiveData<Int>()
    val winningMessage: LiveData<Int> = winningMessageMutable

    private val errorMutable = MutableLiveData<Int>()
    val error: LiveData<Int> = errorMutable

    init {
        for ( line in 0 until Game.BOARD_SIZE) {
            for (column in 0 until Game.BOARD_SIZE) {
                liveDataBoardMutable[line][column].value = game.getPlayerAtPosition(line, column)
            }
        }

        currentPlayerMutable.value = game.getCurrentPlayer()
        winningMessageMutable.value = R.string.winning_while_game
    }

    fun playAtPosition(line: Int, column: Int) {
        if (!game.isFinished()) {
            try {
                game.playAtPosition(line, column)
                liveDataBoardMutable[line][column].value = game.getPlayerAtPosition(line, column)
                currentPlayerMutable.value = game.getCurrentPlayer()
            } catch (playingException: IllegalPlayingPositionException) {
                errorMutable.value = R.string.error_playing_position
            }

            if (game.isFinished()) {
                winningMessageMutable.value = when(game.winner) {
                    Player.X -> R.string.winning_x
                    Player.O -> R.string.winning_o
                    Player.NONE -> R.string.winning_none
                }
            }
        }
    }

    fun errorReceived() {
        errorMutable.value = null
    }
}