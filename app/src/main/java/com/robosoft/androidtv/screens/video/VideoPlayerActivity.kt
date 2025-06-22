package com.robosoft.androidtv.screens.video

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class VideoPlayerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val videoUrl = intent.getStringExtra("video_url")
        setContent {
            videoUrl?.let {
                TVVideoPlayer(videoUrl = it)
            }
        }
    }
}