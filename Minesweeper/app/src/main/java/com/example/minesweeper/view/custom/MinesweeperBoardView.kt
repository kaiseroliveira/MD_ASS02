package com.example.minesweeper.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.convertTo
import kotlin.random.Random
import kotlin.Array

class MinesweeperBoardView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private var sqrtSize = 3
    private var size = 10

    private var cellSizePixels = 0F

    private var selectedRow = -1
    private var selectedCol = -1

    private val paintLine = Paint().apply {this
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 4f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val sizePixels = Math.min(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(sizePixels, sizePixels)
    }

    override fun onDraw(canvas: Canvas) {
        cellSizePixels = (width / size).toFloat()
        fillCells(canvas)
        drawLines(canvas)
    }

    private fun drawText(canvas: Canvas) {
    }

    private val selectedCellPaint = Paint().apply { this
        style = Paint.Style.FILL_AND_STROKE
        color =  Color.parseColor("#FF0000")
    }

    private fun fillCells(canvas: Canvas) {
        for (r in 0..size) {
            for (c in 0..size){
                if (r == selectedRow && c == selectedCol) {
                    fillCell(canvas, r, c, selectedCellPaint)
                }
            }
        }
    }

    private fun fillCell(canvas: Canvas, r: Int, c: Int, paint: Paint) {
        canvas.drawRect(c * cellSizePixels, r * cellSizePixels, (c + 1) * cellSizePixels, (r + 1) * cellSizePixels, paint)
        //canvas.drawText("M", r.toFloat(), c.toFloat(), paint)

    }

    private fun drawLines(canvas: Canvas) {
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paintLine)
        for (i in 1 until size) {
            canvas.drawLine(
                i * cellSizePixels,
                0F,
                i * cellSizePixels,
                height.toFloat(),
                paintLine
            )
            canvas.drawLine(
                0F,
                i * cellSizePixels,
                width.toFloat(),
                i * cellSizePixels,
                paintLine
            )

        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                handleTouchEvent(event.x, event.y)
                true
            }
            else -> false
        }
    }

    private fun handleTouchEvent(x: Float, y:Float) {
        selectedRow = (y / cellSizePixels).toInt()
        selectedCol = (x / cellSizePixels).toInt()
        invalidate()
    }

    private fun boardValueGenerator(canvas: Canvas){
        var mines = 0
        while (mines < 20) {
            for (r in 0..size) {
                var randomGenerator = Random.nextInt(1, 10)
                for (c in 0..size) {
                    if (randomGenerator == c ) {
                        fillCell(canvas, r, c, selectedCellPaint)
                         mines++
                    }
                }
            }
        }


     //UPDATE METHODS
    fun updateSelectedCellUI(row: Int, col: Int) {
         selectedRow = row
         selectedCol = col
         invalidate()
    }



    }

}