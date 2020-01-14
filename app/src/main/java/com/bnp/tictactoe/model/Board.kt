package com.bnp.tictactoe.model

data class Board(val size: Int) {
    val squares: List<MutableList<Player>> = List(size) {
        MutableList(size) {
            Player.NONE
        }
    }
}