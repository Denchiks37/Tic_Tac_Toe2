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

class Game : AppCompatActivity(), OnClickListener{

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

    private var username :String? = null
    private var friendName :String? = null
    private var userSide :String? = null


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
        friendName = intent?.getStringExtra(Const.FRIEND_NAME)?:""

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

        setupUi()
        setupClickListeners()
    }

    private fun setupUi() {
        username = intent?.getStringExtra(Const.USERNAME)
        userSide = intent?.getStringExtra(Const.SELECTEC_SIDE)
        friendName = intent?.getStringExtra(Const.FRIEND_NAME)

        userSide?.also {
            if(it == Const.CIRCLE){
                activePlayer = 0
                userSideMap = Pair(0,"USER")
            } else if(it == Const.CROSS){
                activePlayer = 1
                userSideMap = Pair(1,"USER")
            }
        }


    }

    private fun setupClickListeners() {


            BackGame.setOnClickListener {
                finish()
            }
            First.setOnClickListener (this)
            Second.setOnClickListener (this)
            Third.setOnClickListener (this)
            Fourth.setOnClickListener (this)
            Fifth.setOnClickListener (this)
            Sixth.setOnClickListener (this)
            Seventh.setOnClickListener (this)
            Eigth.setOnClickListener (this)
            Ninth.setOnClickListener (this)
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

        } else if(activePlayer == player2 && filledGrid[position] == -1) {
            clickedImage.setImageDrawable(getDrawable(R.drawable.cross))
            filledGrid[position] = player2

            if(Rules.check(filledGrid)){
                gameEnd(activePlayer)
            }else{
                activePlayer = player1
            }
        }

        if (!filledGrid.contains(-1)) {
            gameEnd(-1)
        }
    }


    private fun gameEnd(activePlayer: Int) {

        val whoWon = if (userSideMap.first == activePlayer) {
            Const.PlayerF
        } else {
            Const.PlayerS
        }

        if (Rules.check(filledGrid)) {
            // A player has won
            val winName = if ((whoWon == Const.PlayerF) && !username.isNullOrEmpty()) {
                username
            } else if ((whoWon == Const.PlayerS) && !friendName.isNullOrEmpty()) {
                friendName
            } else {
                "NA"
            }
            startActivity(Intent(this@Game, ResultActivity::class.java).apply {
                putExtra(Const.WINNER_MES, "$winName ${getString(R.string.Winner)}")
            })
        } else if (filledGrid.all { it != -1 }) {
                // The game is a draw
                startActivity(Intent(this@Game, ResultActivity::class.java).apply {
                    putExtra(Const.WINNER_MES, getString(R.string.Draw))
                })
            }

        }
}
