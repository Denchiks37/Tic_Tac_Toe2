package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class SecondPlayer : AppCompatActivity() {

    lateinit var GetBack2:ImageButton
    lateinit var editUsername2:TextInputEditText
    lateinit var Next2:Button

    private var username :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_second_player)

        initialiseViews()
    }

    private fun initialiseViews(){
        GetBack2 = findViewById(R.id.GetBack2)
        editUsername2 = findViewById(R.id.editUsername2)
        Next2 = findViewById(R.id.Next2)

        username = intent?.getStringExtra(Const.USERNAME)

        setupClickListeners()
    }

    private fun setupClickListeners(){
        GetBack2.setOnClickListener {
            finish()
        }

        Next2.setOnClickListener{
            redirectToChooseSide()
        }
    }

    private fun redirectToChooseSide(){
        val friendName = editUsername2.text.toString()
        if(friendName.isNullOrEmpty()){
            editUsername2.error = getString(R.string.please_enter_username)
            Toast.makeText(this,getString(R.string.please_enter_username),Toast.LENGTH_SHORT).show()
        } else {
            startActivity(
                Intent(this@SecondPlayer, ChooseSide::class.java).apply{
                    putExtra(Const.USERNAME, username)
                    putExtra(Const.FRIEND_NAME, friendName)
                })
        }
    }
}