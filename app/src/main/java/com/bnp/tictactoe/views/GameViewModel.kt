package com.bnp.tictactoe.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bnp.tictactoe.business.Game
import com.bnp.tictactoe.model.Player

class GameViewModel : ViewModel() {

    private val game = Game()

    private val zeroZeroMutable = MutableLiveData<Player>()
    val zeroZero: LiveData<Player> = zeroZeroMutable
    private val zeroOneMutable = MutableLiveData<Player>()
    val zeroOne: LiveData<Player> = zeroOneMutable
    private val zeroTwoMutable = MutableLiveData<Player>()
    val zeroTwo: LiveData<Player> = zeroTwoMutable
    private val oneZeroMutable = MutableLiveData<Player>()
    val oneZero: LiveData<Player> = oneZeroMutable
    private val oneOneMutable = MutableLiveData<Player>()
    val oneOne: LiveData<Player> = oneOneMutable
    private val oneTwoMutable = MutableLiveData<Player>()
    val oneTwo: LiveData<Player> = oneTwoMutable
    private val twoZeroMutable = MutableLiveData<Player>()
    val twoZero: LiveData<Player> = twoZeroMutable
    private val twoOneMutable = MutableLiveData<Player>()
    val twoOne: LiveData<Player> = twoOneMutable
    private val twoTwoMutable = MutableLiveData<Player>()
    val twoTwo: LiveData<Player> = twoTwoMutable

    init {
        zeroZeroMutable.value = game.getPlayerAtPosition(0, 0)
        zeroOneMutable.value = game.getPlayerAtPosition(0, 1)
        zeroTwoMutable.value = game.getPlayerAtPosition(0, 2)
        oneZeroMutable.value = game.getPlayerAtPosition(1, 0)
        oneOneMutable.value = game.getPlayerAtPosition(1, 1)
        oneTwoMutable.value = game.getPlayerAtPosition(1, 2)
        twoZeroMutable.value = game.getPlayerAtPosition(2, 0)
        twoOneMutable.value = game.getPlayerAtPosition(2, 1)
        twoTwoMutable.value = game.getPlayerAtPosition(2, 2)
    }

}