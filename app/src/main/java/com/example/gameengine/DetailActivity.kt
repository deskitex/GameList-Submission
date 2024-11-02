package com.example.gameengine

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gameengine.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val dataGame = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("key_games", Game::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("key_games")
        }
        if (dataGame != null) {
            binding.tvDetailName.text = dataGame.name
            binding.tvDetailDev.text = dataGame.dev
            binding.tvDetailDate.text = dataGame.date
            binding.tvDetailDescription.text = dataGame.desc
            binding.tvDetailFeatures.text = dataGame.feature
            binding.ivDetailPhoto.setImageResource(dataGame.photo)
            binding.ivDetailThumb.setImageResource(dataGame.thumb)
        }

        binding.btnShare.setOnClickListener{
            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.setType("text/plain")
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this ${dataGame?.name} game!")
            startActivity(sendIntent)
        }

        binding.btnSteam.setOnClickListener{
            val uri = Uri.parse(dataGame?.steam)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
     }

}