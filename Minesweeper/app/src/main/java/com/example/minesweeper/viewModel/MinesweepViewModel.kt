package com.example.minesweeper.viewModel

import androidx.lifecycle.ViewModel
import com.example.minesweeper.game.MinesweepGame

class MinesweepViewModel: ViewModel() {

    val minesweepGame = MinesweepGame()
}