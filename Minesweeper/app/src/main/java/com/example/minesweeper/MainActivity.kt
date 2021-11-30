package com.example.minesweeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.minesweeper.view.custom.MinesweeperBoardView
import com.example.minesweeper.viewModel.MinesweepViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MinesweepViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //viewModel specific for this activity
        viewModel = ViewModelProvider(this)[MinesweepViewModel::class.java]
        viewModel.MinesweepGame.selectedCellLiveData.obeserve(this, Observer {updateSelectedCellUI(it)})
    }

    private fun updateSelectedCellUI(cell: Pair<Int, Int>? ) = cell?.let {
        MinesweeperBoardView.updateSelectedCellUI(cell.first, cell.second)
    }

}