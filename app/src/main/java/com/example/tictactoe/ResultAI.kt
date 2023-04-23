
package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView

class ResultAI : AppCompatActivity() {

    private var winnerMsg: String?=null
    lateinit var WinTeam: TextView
    lateinit var PlayAgain2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_result_ai)

        setupUI()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        PlayAgain2.setOnClickListener{
            val intent = Intent(this@ResultAI, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun setupUI() {
        winnerMsg = intent?.getStringExtra(Const.WINNER_MES)

        WinTeam = findViewById(R.id.resultText2)
        PlayAgain2 = findViewById(R.id.PlayAgain2)

        val resultText2 = intent.getStringExtra(Const.WINNER_MES)
        findViewById<TextView>(R.id.resultText2).text = resultText2


        WinTeam.text = winnerMsg
    }
}