package com.example.minesweeper.game

import androidx.lifecycle.MutableLiveData

class MinesweepGame {

    //using live data to update board
    var selectedCellLiveData = MutableLiveData<Pair<Int, Int>>()

    private  var selectedRow = -1
    private var selectedCol = - 1

    init {
        selectedCellLiveData.postValue(Pair(selectedRow, selectedCol))

    }

    fun upadateSelectedCell(row: Int, col: Int) {
        selectedRow = row
        selectedCol - col
        selectedCellLiveData.postValue(Pair(row, col))
    }

}