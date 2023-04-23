package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class UserVsAI : AppCompatActivity() {

    lateinit var GetBack:ImageButton
    lateinit var editUsername:TextInputEditText
    lateinit var Next:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_players)

        initialiseViews()
    }

    private fun initialiseViews(){
        GetBack = findViewById(R.id.GetBack)
        editUsername = findViewById(R.id.editUsername)
        Next = findViewById(R.id.Next)

        setupClickListeners()
    }

    private fun setupClickListeners(){
        GetBack.setOnClickListener {
            finish()
        }

        Next.setOnClickListener{
            redirectToChooseSide()
        }
    }

    private fun redirectToChooseSide(){
        val userName = editUsername.text.toString()
        if(userName.isNullOrEmpty()){
            editUsername.error = getString(R.string.please_enter_username)
            Toast.makeText(this,getString(R.string.please_enter_username),Toast.LENGTH_SHORT).show()
        } else {
            startActivity(
                Intent(this@UserVsAI, ChooseSideAI::class.java).apply{
                    putExtra(Const.USERNAME, userName)
                })
        }
    }
}