package com.dicoding.movflix

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvMovies: RecyclerView
    private val list = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        rvMovies = findViewById(R.id.rv_movies)
        rvMovies.setHasFixedSize(true)

        list.addAll(getListMovies())
        showRecyclerList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this@MainActivity, About::class.java)
                startActivity(intent)
            }


        }

        return super.onOptionsItemSelected(item)
    }

    private fun getListMovies(): ArrayList<Movie> {
        val dataName = resources.getStringArray(R.array.movie_name)
        val dataYear = resources.getStringArray(R.array.movie_year)
        val dataGenre = resources.getStringArray(R.array.movie_genre)
        val dataPhoto = resources.getStringArray(R.array.movie_photo)
        val dataPlot = resources.getStringArray(R.array.movie_plot)


        val listMovies = ArrayList<Movie>()
        for (i in dataName.indices) {
            val movies = Movie(dataName[i], dataYear[i], dataGenre[i], dataPhoto[i], dataPlot[i])
            listMovies.add(movies)
        }
        return listMovies
    }

    private fun showSelectedMovies(movie: Movie) {

        val moveIntentDetail = Intent(this@MainActivity, MovieDetail::class.java)
        moveIntentDetail.putExtra(MovieDetail.EXTRA_MOVIE, movie)
        startActivity(moveIntentDetail)

    }

    private fun showRecyclerList() {
        rvMovies.layoutManager = LinearLayoutManager(this)
        val listMoviesAdapter = ListMovieAdapter(list)
        rvMovies.adapter = listMoviesAdapter


        listMoviesAdapter.setOnItemClickCallback(object : ListMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Movie) {
                showSelectedMovies(data)
            }
        })
    }

}

