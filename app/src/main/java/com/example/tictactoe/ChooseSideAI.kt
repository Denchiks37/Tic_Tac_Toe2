package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets.Side
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ChooseSideAI : AppCompatActivity() {

    lateinit var GetBackSide2: ImageButton
    lateinit var SideCont2: LinearLayout
    lateinit var ChoosedSide2: TextView
    lateinit var NextSide2: Button
    lateinit var Sides2: RadioGroup
    lateinit var ChooseSideText2: TextView

    var username :String? = null
    var yourSide :String? = null

    private val player1 = 0
    private val player2 = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_choose_side_ai)

        username = intent?.getStringExtra(Const.USERNAME)?:""

        initialiseViews()
    }

    private fun initialiseViews(){
        GetBackSide2 = findViewById(R.id.GetBackSide2)
        ChoosedSide2 = findViewById(R.id.ChoosedSide2)
        NextSide2 = findViewById(R.id.NextSide2)
        Sides2 = findViewById(R.id.Sides2)
        SideCont2 = findViewById(R.id.SideCont2)
        ChooseSideText2 = findViewById(R.id.ChooseSideText2)

        username?.also {
            ChooseSideText2.text = "$it Choose your side"
        }
        setupClickListeners()
    }

    private fun setupClickListeners(){

        Sides2.setOnCheckedChangeListener{ _, checkedId ->
            if (checkedId == R.id.circle2){
                yourSide = Const.CIRCLE
                ChoosedSide2.text = "${getString(R.string.you_choosed)} $yourSide"
            }
        }

        GetBackSide2.setOnClickListener {
            finish()
        }

        NextSide2.setOnClickListener {
            yourSide?.also {
                if(yourSide.isNullOrEmpty()){
                    Toast.makeText(this, getString(R.string.choose_your_side),Toast.LENGTH_SHORT).show()
                } else{
                    goToGame()
                }
            }
        }
    }

    private fun goToGame() {
        startActivity(Intent (this@ChooseSideAI, GameAI::class.java).apply{
            putExtra(Const.USERNAME , username)
            putExtra(Const.SELECTEC_SIDE , yourSide)
            putExtra(Const.FIRST_PLAYER, if (yourSide == Const.CROSS) player1 else player2)
        })
    }
}