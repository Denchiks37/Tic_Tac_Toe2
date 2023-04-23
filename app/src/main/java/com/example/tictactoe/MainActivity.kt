package com.example.tictactoe

import android.content.Intent
import android.media.tv.TvContract.Channels.Logo
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {

    lateinit var ivLogo: ImageView
    lateinit var Mode: TextView
    lateinit var PvP: Button
    lateinit var PvC: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        installSplashScreen()
        setContentView(R.layout.activity_main)

        initialiseViews()
    }

    private fun initialiseViews() {
        ivLogo = findViewById(R.id.ivAppLogo)
        Mode = findViewById(R.id.Mode)
        PvP = findViewById(R.id.PvP)
        PvC = findViewById(R.id.PvC)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        PvP.setOnClickListener {
            startActivity(
                Intent(this@MainActivity, Players::class.java)
            )
        }
        PvC.setOnClickListener {
            startActivity(
                Intent(this@MainActivity, UserVsAI::class.java)
            )
        }

    }
}