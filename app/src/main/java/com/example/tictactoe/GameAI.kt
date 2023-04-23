package com.example.tictactoe

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.Window
import android.widget.ImageView
import android.widget.TextView

class GameAI : AppCompatActivity(), OnClickListener{

    lateinit var BackGame: ImageView

    lateinit var First: ImageView
    lateinit var Second: ImageView
    lateinit var Third: ImageView
    lateinit var Fourth: ImageView
    lateinit var Fifth: ImageView
    lateinit var Sixth: ImageView
    lateinit var Seventh: ImageView
    lateinit var Eigth: ImageView
    lateinit var Ninth: ImageView


    private var userSideMap = Pair(-1,"")
    private var userSymbol = R.drawable.circle


    private var username :String? = null
    private var userSide :String? = null

    private val imageBoxes = listOf(R.id.First,R.id.Second,R.id.Third,R.id.Fourth,R.id.Fifth,R.id.Sixth,R.id.Seventh,R.id.Eigth,R.id.Ninth)

    private val filledGrid = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)

    private val player1 = 0
    private val player2 = 1

    private var activePlayer = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_game2)


        username = intent?.getStringExtra(Const.USERNAME)?:""

        initialiseViews()
    }

    private fun initialiseViews() {
        BackGame = findViewById(R.id.BackGame)
        First = findViewById(R.id.First)
        Second = findViewById(R.id.Second)
        Third = findViewById(R.id.Third)
        Fourth = findViewById(R.id.Fourth)
        Fifth = findViewById(R.id.Fifth)
        Sixth = findViewById(R.id.Sixth)
        Seventh = findViewById(R.id.Seventh)
        Eigth = findViewById(R.id.Eigth)
        Ninth = findViewById(R.id.Ninth)

        if (userSide == Const.CIRCLE) {
            userSymbol = R.drawable.cross
            activePlayer = 1
        }

        setupUi()
        setupClickListeners()
    }

    private fun setupUi() {
        username = intent?.getStringExtra(Const.USERNAME)
        userSide = intent?.getStringExtra(Const.SELECTEC_SIDE)

        userSide?.also {
            if (it == Const.CIRCLE) {
                activePlayer = 0
                userSideMap = if (it == Const.CIRCLE) {
                    Pair(1, "AI")
                } else {
                    Pair(0, "AI")
                }
            } else if (it == Const.CROSS) {
                activePlayer = 1
                userSymbol = R.drawable.cross
                userSideMap = if (it == Const.CIRCLE) {
                    Pair(0, "AI")
                } else {
                    Pair(1, "AI")
                }
            }
        }
    }


    private fun setupClickListeners() {


            BackGame.setOnClickListener {
                finish()
            }
            First.setOnClickListener(this)
            Second.setOnClickListener(this)
            Third.setOnClickListener(this)
            Fourth.setOnClickListener(this)
            Fifth.setOnClickListener(this)
            Sixth.setOnClickListener(this)
            Seventh.setOnClickListener(this)
            Eigth.setOnClickListener(this)
            Ninth.setOnClickListener(this)
        }


    override fun onClick(view: View) {

        val clickedImage = findViewById<ImageView>(view.id)
        val clickedImageTag = view?.tag.toString()?.toInt()?:0
        val position = clickedImageTag-1

        if(activePlayer == player1 && filledGrid[position] == -1) {
            clickedImage.setImageDrawable(getDrawable(R.drawable.circle))
            filledGrid[position] = player1

            if(Rules.check(filledGrid)){
                gameEnd(activePlayer)
            }else{
                activePlayer = player2
            }

            if (!filledGrid.contains(-1)) {
                gameEnd(-1)
            }

        } else if(activePlayer == player2 && filledGrid[position] == -1) {
            clickedImage.setImageDrawable(getDrawable(R.drawable.cross))
            filledGrid[position] = player2

            if(Rules.check(filledGrid)){
                gameEnd(activePlayer)
            }else{
                activePlayer = player1
            }

            if (!filledGrid.contains(-1)) {
                gameEnd(-1)
            }
        }

        // If it's the AI's turn, make a move
        if (activePlayer == player2) {
            val ai = AI()
            val aiMove = ai.makeMove(filledGrid)
            if (aiMove != -1) {
                val aiImage = findViewById<ImageView>(imageBoxes[aiMove])
                aiImage.setImageDrawable(getDrawable(R.drawable.cross))
                filledGrid[aiMove] = player2

                if (Rules.check(filledGrid)) {
                    gameEnd(activePlayer)
                } else {
                    activePlayer = player1
                }

                if (!filledGrid.contains(-1)) {
                    gameEnd(-1)
                }
            }
        }
    }



    class AI {

        private val player1 = 0
        private val player2 = 1
        fun makeMove(filledGrid: IntArray): Int {
            // Check for a winning move for the AI
            for (i in 0 until 9) {
                if (filledGrid[i] == -1) {
                    filledGrid[i] = player2
                    if (Rules.check(filledGrid)) {
                        filledGrid[i] = -1
                        return i
                    }
                    filledGrid[i] = -1
                }
            }

            // Check for a winning move for the user and block it
            for (i in 0 until 9) {
                if (filledGrid[i] == -1) {
                    filledGrid[i] = player1
                    if (Rules.check(filledGrid)) {
                        filledGrid[i] = -1
                        return i
                    }
                    filledGrid[i] = -1
                }
            }

            // Choose a random empty cell
            val emptyCells = filledGrid.indices.filter { filledGrid[it] == -1 }
            if (emptyCells.isNotEmpty()) {
                val randomIndex = (0 until emptyCells.size).random()
                return emptyCells[randomIndex]
            }

            return -1 // This should never happen
        }
    }





    private fun gameEnd(activePlayer: Int) {

        val AI = "Computer"

        val whoWon = if (userSideMap.first == activePlayer) {
            if (userSideMap.second == "USER") Const.PlayerF else AI
        } else {
            if (userSideMap.second == "AI") Const.PlayerF else AI
        }

        if (Rules.check(filledGrid)) {
            // A player has won
            val winName = if ((whoWon == Const.PlayerF) && !username.isNullOrEmpty()) {
                username
            } else if ((whoWon == Const.COMPUTER)) {
                AI
            } else{
                "Computer"
        }
            startActivity(Intent(this@GameAI, ResultAI::class.java).apply {
                putExtra(Const.WINNER_MES, "$winName ${getString(R.string.Winner)}")
            })
        } else if (filledGrid.all { it != -1 }) {
            // The game is a draw
            startActivity(Intent(this@GameAI, ResultAI::class.java).apply {
                putExtra(Const.WINNER_MES, getString(R.string.Draw))
            })
        }
    }
}
