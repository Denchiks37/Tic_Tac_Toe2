package com.example.tictactoe

object Rules {

    val winPos = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(3, 6, 9),
        intArrayOf(1, 5, 9),
        intArrayOf(3, 5, 7),
    )

    fun check(filledGrid: IntArray): Boolean {
        var Winner = false
        winPos.forEachIndexed{index, item ->
            val value1 = winPos[index][0]
            val value2 = winPos[index][1]
            val value3 = winPos[index][2]

            if(filledGrid[value1-1] == filledGrid[value2-1]
                && filledGrid[value2-1] == filledGrid[value3-1]){

                if (filledGrid[value1-1] != -1){
                    Winner = true
                }
            }
        }
        return Winner
    }
}