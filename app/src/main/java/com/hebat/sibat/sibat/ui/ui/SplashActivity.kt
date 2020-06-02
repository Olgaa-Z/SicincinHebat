package com.hebat.sibat.sibat.ui.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.hebat.sibat.sibat.R

class SplashActivity : AppCompatActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        handler = Handler()

        handler.postDelayed(Runnable {
            var intent = intent
            intent = Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
            this@SplashActivity.finish()
        }, 5000)
    }
}
