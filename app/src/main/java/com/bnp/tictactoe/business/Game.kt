package com.bnp.tictactoe.business

import com.bnp.tictactoe.model.Board
import com.bnp.tictactoe.model.Player

class Game {
    private val board: Board = Board(BOARD_SIZE)
    private val currentPlayer: Player = Player.X

    fun getCurrentPlayer() : Player {
        return currentPlayer
    }

    fun getPlayerAtPosition(line: Int, column: Int): Player {
        if (line !in 0 until board.size) {
            throw IllegalArgumentException("The line in argument is outside the board")
        }
        if (column !in 0 until board.size) {
            throw IllegalArgumentException("The column in argument is outside the board")
        }
        return board.squares[line][column]
    }

    companion object {
        const val BOARD_SIZE = 3
    }
}