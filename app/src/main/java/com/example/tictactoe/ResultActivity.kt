
package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    private var winnerMsg: String?=null
    lateinit var WinTeam: TextView
    lateinit var PlayAgain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_result)

        setupUI()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        PlayAgain.setOnClickListener{
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun setupUI() {
        winnerMsg = intent?.getStringExtra(Const.WINNER_MES)

        WinTeam = findViewById(R.id.resultText)
        PlayAgain = findViewById(R.id.PlayAgain)

        val resultText = intent.getStringExtra(Const.WINNER_MES)
        findViewById<TextView>(R.id.resultText).text = resultText


        WinTeam.text = winnerMsg
    }
}