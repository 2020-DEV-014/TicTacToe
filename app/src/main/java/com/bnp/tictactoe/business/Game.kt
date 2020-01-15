package com.bnp.tictactoe.business

import com.bnp.tictactoe.model.Board
import com.bnp.tictactoe.model.Player

class Game {
    private val board: Board = Board(BOARD_SIZE)
    private var currentPlayer: Player = Player.X
    var winner: Player = Player.NONE
        private set

    fun getCurrentPlayer(): Player {
        return currentPlayer
    }

    fun getPlayerAtPosition(line: Int, column: Int): Player {
        checkPosition(line, column)
        return board.squares[line][column]
    }

    private fun checkPosition(line: Int, column: Int) {
        if (line !in 0 until board.size) {
            throw IllegalArgumentException("The line in argument is outside the board")
        }
        if (column !in 0 until board.size) {
            throw IllegalArgumentException("The column in argument is outside the board")
        }
    }

    fun playAtPosition(line: Int, column: Int) {
        if (winner.isPlayer) {
            throw IllegalStateException("The game is finished")
        }
        if (getPlayerAtPosition(line, column) != Player.NONE) {
            throw IllegalPlayingPositionException("This square is already played")
        }
        board.squares[line][column] = currentPlayer
        currentPlayer = if (currentPlayer == Player.X) {
            Player.O
        } else {
            Player.X
        }

        updateWinner()
    }

    fun isFinished(): Boolean {
        if (winner.isPlayer) {
            return true
        } else {
            board.squares.forEach { line ->
                line.forEach { player ->
                    if (!player.isPlayer) {
                        return false
                    }
                }
            }
            return true
        }
    }

    private fun updateWinner() {
        var topLeftCheckPlayer: Player = board.squares[0][0]
        var bottomLeftCheckPlayer: Player = board.squares[BOARD_SIZE - 1][0]

        fun checkDownwardLine(index: Int) {
            if (topLeftCheckPlayer.isPlayer) {
                val currentlyCheckedPlayer = board.squares[index][index]
                topLeftCheckPlayer = if (currentlyCheckedPlayer == topLeftCheckPlayer) {
                    currentlyCheckedPlayer
                } else {
                    Player.NONE
                }
            }
        }

        fun checkUpwardLine(index: Int) {
            if (bottomLeftCheckPlayer.isPlayer) {
                val currentlyCheckedPlayer = board.squares[BOARD_SIZE - 1 - index][index]
                bottomLeftCheckPlayer = if (currentlyCheckedPlayer == bottomLeftCheckPlayer) {
                    currentlyCheckedPlayer
                } else {
                    Player.NONE
                }
            }
        }

        for (index in 0 until BOARD_SIZE) {
            val winnerOfTheLine = checkWinningLine(index)
            if (winnerOfTheLine.isPlayer) {
                winner =  winnerOfTheLine
                return
            }
            val winnerOfTheColumn = checkWinningColumn(index)
            if (winnerOfTheColumn.isPlayer) {
                winner =  winnerOfTheColumn
                return
            }
            if (index > 0) {
                checkDownwardLine(index)
                checkUpwardLine(index)
            }
        }

        winner =  if (topLeftCheckPlayer.isPlayer) {
            topLeftCheckPlayer
        } else {
            bottomLeftCheckPlayer
        }
    }

    private fun checkWinningLine(line: Int): Player {
        val currentlyCheckPlayer = board.squares[line][0]
        if (!currentlyCheckPlayer.isPlayer) {
            return Player.NONE
        }
        for (column in 1 until BOARD_SIZE) {
            if (board.squares[line][column] != currentlyCheckPlayer) {
                return Player.NONE
            } else if (column == BOARD_SIZE - 1) {
                return currentlyCheckPlayer
            }
        }
        return Player.NONE
    }

    private fun checkWinningColumn(column: Int): Player {
        val currentlyCheckPlayer = board.squares[0][column]
        if (!currentlyCheckPlayer.isPlayer) {
            return Player.NONE
        }
        for (line in 1 until BOARD_SIZE) {
            if (board.squares[line][column] != currentlyCheckPlayer) {
                return Player.NONE
            } else if (line == BOARD_SIZE - 1) {
                return currentlyCheckPlayer
            }
        }
        return Player.NONE
    }

    companion object {
        const val BOARD_SIZE = 3
    }
}