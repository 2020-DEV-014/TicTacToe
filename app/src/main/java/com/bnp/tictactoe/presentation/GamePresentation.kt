package com.bnp.tictactoe.presentation

import com.bnp.tictactoe.model.Player

val Player.textPresentation: String
    get() = when (this) {
        Player.NONE -> ""
        Player.X -> "X"
        Player.O -> "O"
    }