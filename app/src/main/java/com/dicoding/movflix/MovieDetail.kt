package com.dicoding.movflix

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MovieDetail : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"

    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportActionBar?.title = ""


        val movieImage: ImageView = findViewById(R.id.image_movies)

        val movieTitle: TextView = findViewById(R.id.title_movies)

        val movieYear: TextView = findViewById(R.id.year_movies)

        val moviePlot: TextView = findViewById(R.id.plot_movies)

        val movies = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Movie>(EXTRA_MOVIE, Movie::class.java)

        } else {
            @Suppress("DEPRECATION") intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        }

        if (movies !== null) {
            val text = movies.name
            movieTitle.text = text

            val textYear = "${movies.year} - ${movies.genre}"
            movieYear.text = textYear

            Glide.with(this).load(movies.photo).placeholder(R.drawable.splash1)
                .error(R.drawable.splash1).into(movieImage)

            val textPlot = movies.plot
            moviePlot.text = textPlot

        }
    }

    fun encodeUrl(url: String): String {
        try {
            // Use UTF-8 encoding
            val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
            return encodedUrl
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return url  // Return the original URL in case of an exception
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share_button -> {
                val movies = if (Build.VERSION.SDK_INT >= 33) {
                    intent.getParcelableExtra<Movie>(EXTRA_MOVIE, Movie::class.java)

                } else {
                    @Suppress("DEPRECATION") intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
                }

                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type =
                    "text/plain"
                val originalUrl = if (movies !== null) {
                    movies.name
                } else {
                    ""
                }
                val encodedUrl = encodeUrl(originalUrl)
               
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT, "https://en.wikipedia.org/w/index.php?search=$encodedUrl"
                )

                if (shareIntent.resolveActivity(packageManager) != null) {
                    val chooser = Intent.createChooser(shareIntent, "Share via")


                    startActivity(chooser)
                } else {
                    Toast.makeText(this, "No app can handle the share intent", Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }

        return super.onOptionsItemSelected(item)
    }
}