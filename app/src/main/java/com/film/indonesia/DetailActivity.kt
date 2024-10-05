package com.film.indonesia

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val photoUrl = intent.getStringExtra("photoUrl")
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val director = intent.getStringExtra("director")
        val year = intent.getStringExtra("year")
        val viewers = intent.getIntExtra("viewers", 0)

        val formattedViewers = NumberFormat.getInstance(Locale("id", "ID")).format(viewers)

        findViewById<TextView>(R.id.tv_detail_title).text = title
        findViewById<TextView>(R.id.tv_detail_desc).text = description
        findViewById<TextView>(R.id.tv_detail_director_value).text = director
        findViewById<TextView>(R.id.tv_detail_year_value).text = year
        findViewById<TextView>(R.id.tv_detail_views_value).text = formattedViewers
        val imageView = findViewById<ImageView>(R.id.img_detail_photo)
        Glide.with(this)
            .load(photoUrl)
            .into(imageView)

        val btnShare:Button = findViewById(R.id.btn_share)
        btnShare.setOnClickListener {
            shareContent()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun shareContent() {
        val shareText = findViewById<TextView>(R.id.tv_detail_title).text.toString()
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
        val chooser = Intent.createChooser(shareIntent, "Share with ")
        startActivity(chooser)
    }
}