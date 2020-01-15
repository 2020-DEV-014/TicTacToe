package com.bnp.tictactoe.business

import com.bnp.tictactoe.model.Board
import com.bnp.tictactoe.model.Player

class Game {
    private val board: Board = Board(BOARD_SIZE)
    private var currentPlayer: Player = Player.X

    fun getCurrentPlayer() : Player {
        return currentPlayer
    }

    fun getPlayerAtPosition(line: Int, column: Int): Player {
        checkPosition(line, column)
        return board.squares[line][column]
    }

    fun playAtPosition(line: Int, column: Int) {
        if (getPlayerAtPosition(line, column) != Player.NONE) {
            throw IllegalArgumentException("This square is already played")
        }
        board.squares[line][column] = currentPlayer
        currentPlayer = if (currentPlayer == Player.X) {
            Player.O
        } else {
            Player.X
        }
    }

    private fun checkPosition(line: Int, column: Int) {
        if (line !in 0 until board.size) {
            throw IllegalArgumentException("The line in argument is outside the board")
        }
        if (column !in 0 until board.size) {
            throw IllegalArgumentException("The column in argument is outside the board")
        }
    }

    companion object {
        const val BOARD_SIZE = 3
    }
}