package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets.Side
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ChooseSide : AppCompatActivity() {

    lateinit var BackSide: ImageButton
    lateinit var SideCont: LinearLayout
    lateinit var ChoosedSide: TextView
    lateinit var NextSide: Button
    lateinit var Sides: RadioGroup
    lateinit var ChooseSideText: TextView

    var username :String? = null
    var friendName :String? = null
    var yourSide :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_choose_side)

        username = intent?.getStringExtra(Const.USERNAME)?:""
        friendName = intent?.getStringExtra(Const.FRIEND_NAME)?:""

        initialiseViews()
    }

    private fun initialiseViews(){
        BackSide = findViewById(R.id.BackSide)
        ChoosedSide = findViewById(R.id.ChoosedSide)
        NextSide = findViewById(R.id.NextSide)
        Sides = findViewById(R.id.Sides)
        SideCont = findViewById(R.id.SideCont)
        ChooseSideText = findViewById(R.id.ChooseSideText)

        username?.also {
            ChooseSideText.text = "$it Choose your side"
        }
        setupClickListeners()
    }

    private fun setupClickListeners(){

        Sides.setOnCheckedChangeListener{ _, checkedId ->
            if(checkedId == R.id.cross){
                yourSide = Const.CROSS
                ChoosedSide.text = "${getString(R.string.you_choosed)} $yourSide"
            } else if (checkedId == R.id.circle){
             yourSide = Const.CIRCLE
                ChoosedSide.text = "${getString(R.string.you_choosed)} $yourSide"
            }
        }

        BackSide.setOnClickListener {
            finish()
        }

        NextSide.setOnClickListener {
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
        startActivity(Intent (this@ChooseSide, Game::class.java).apply{
            putExtra(Const.USERNAME , username)
            putExtra(Const.SELECTEC_SIDE , yourSide)
            putExtra(Const.FRIEND_NAME, friendName)
        })
    }
}