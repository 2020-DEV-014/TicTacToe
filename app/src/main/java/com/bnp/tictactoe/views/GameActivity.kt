package com.bnp.tictactoe.views

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bnp.tictactoe.R
import com.bnp.tictactoe.databinding.ActivityGameBinding

class GameActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        val binding = DataBindingUtil
            .setContentView<ActivityGameBinding>(this, R.layout.activity_game)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.error.observe(this, Observer {
            if (it != null) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.errorReceived()
            }
        })
    }

}