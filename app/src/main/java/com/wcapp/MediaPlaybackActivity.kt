package com.wcapp

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.wcapp.databinding.ActivityMediaPlaybackBinding

class MediaPlaybackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMediaPlaybackBinding
    private var exoPlayer: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaPlaybackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaUrl = intent.getStringExtra("VIDEO_URL")
        val title = intent.getStringExtra("TITLE")

        setupActionBar(title)
        setupExoPlayer(mediaUrl)
    }

    private fun setupActionBar(title: String?) {
        supportActionBar?.apply {
            this.title = title
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupExoPlayer(mediaUrl: String?) {
        if (mediaUrl != null) {
            exoPlayer = ExoPlayer.Builder(this).build()
            binding.playerView.player = exoPlayer
            val mediaItem = MediaItem.fromUri(Uri.parse(mediaUrl))
            exoPlayer?.setMediaItem(mediaItem)
            exoPlayer?.prepare()
            exoPlayer?.play()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.release()
        exoPlayer = null
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
