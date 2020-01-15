package com.bnp.tictactoe.business

class IllegalPlayingPositionException(message: String? = null, cause: Throwable? = null) :
    IllegalArgumentException(message, cause)