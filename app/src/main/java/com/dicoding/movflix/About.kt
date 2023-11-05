package com.dicoding.movflix

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class About : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""


        val imageView = findViewById<ImageView>(R.id.img_item_photo_about)
        val imageUrl = R.drawable.image_me_crop
        val cornerRadius = 100 // Set the desired corner radius

        val requestOptions = RequestOptions().transforms(
            CenterCrop(),
            RoundedCorners(cornerRadius) // Set the corner radius
        )



        Glide.with(this)
            .load(imageUrl)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }


}