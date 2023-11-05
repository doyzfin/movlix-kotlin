package com.dicoding.movflix

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListMovieAdapter(private val listMovie: ArrayList<Movie>) :
    RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_movie, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, year,genre, photo) = listMovie[position]
        Glide.with(holder.itemView.context).load(photo).into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvYear.text = year
        holder.tvGenre.text = genre
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listMovie[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listMovie.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvYear: TextView = itemView.findViewById(R.id.tv_item_year)
        val tvGenre: TextView = itemView.findViewById(R.id.tv_item_genre)

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }

}