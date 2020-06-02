package com.hebat.sibat.sibat.ui.ui

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.hebat.sibat.sibat.R

class Video : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    lateinit var yb: YouTubePlayerView

    override fun onCreate(p0: Bundle?) {
        super.onCreate(p0)
        setContentView(R.layout.video)

        yb=findViewById(R.id.YoutubePlayer)
        yb.initialize("IzaSyAYmbOhDRWXLw3Oj4XxtC13RSZkMZ9QIw4",this)
        requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        p1!!.setFullscreen(true)
        p1.cueVideo("pX8FEUwEt1o")
        p1.play()

    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        if (p1!!.isUserRecoverableError){
            p1.getErrorDialog(this,1).show()
        }
    }




}
