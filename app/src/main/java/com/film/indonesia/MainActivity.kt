package com.film.indonesia

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvFilms: RecyclerView
    private val list = ArrayList<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvFilms = findViewById(R.id.rv_films)
        rvFilms.setHasFixedSize(true)
        list.addAll(getListFilms())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_page ->{
                val moveIntent = Intent(this@MainActivity, About::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListFilms(): ArrayList<Film> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val dataDirector = resources.getStringArray(R.array.data_director)
        val dataYear = resources.getStringArray(R.array.data_year)
        val dataView = resources.getIntArray(R.array.data_views)
        val listFilm = ArrayList<Film>()
        for (i in dataTitle.indices) {
            val film = Film(
                dataTitle[i],
                dataDescription[i],
                dataPhoto[i],
                dataGenre[i],
                dataDirector[i],
                dataYear[i],
                dataView[i])
            listFilm.add(film)
        }
        return listFilm
    }

    private fun showRecyclerList() {
        rvFilms.layoutManager = LinearLayoutManager(this)
        val listFilmAdapter = ListFilmAdapter(list)
        rvFilms.adapter = listFilmAdapter

        listFilmAdapter.setOnItemClickCallback(object : ListFilmAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Film) {
                showSelectedFilm(data)
            }
        })
    }

    private fun showSelectedFilm(film: Film) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("photoUrl", film.photo)
        intent.putExtra("title", film.title)
        intent.putExtra("description", film.description)
        intent.putExtra("genre", film.genre)
        intent.putExtra("director", film.director)
        intent.putExtra("year", film.year)
        intent.putExtra("viewers", film.viewers)
        startActivity(intent)
    }
}