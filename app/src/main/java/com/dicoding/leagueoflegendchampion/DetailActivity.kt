package com.dicoding.leagueoflegendchampion

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.dicoding.leagueoflegendchampion.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val CHAMPIONS = "champion"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var champion: Champion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        champion = intent.getParcelableExtra(CHAMPIONS)!!
        if (champion != null) {
            Glide.with(this)
                .load(champion.photo)
                .into(binding.detailCover)
            binding.detailName.text = champion.name
            binding.detailAlias.text = champion.alias
            binding.detailDescription.text = champion.description
            binding.detailDificulty.text = champion.dificulty
            binding.detailRole.text = champion.role
        }

        binding.actionShare.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id){
            binding.actionShare.id -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "check out this Champion ${champion.name}\n ${champion.description}"
                )
                startActivity(Intent.createChooser(shareIntent, "sharre using"))
            }
        }
    }
}