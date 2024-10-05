package com.film.indonesia

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.film.indonesia.databinding.ItemRowFilmBinding

class ListFilmAdapter(private val listFilm: ArrayList<Film>) :RecyclerView.Adapter<ListFilmAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowFilmBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowFilmBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listFilm.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, description, photo, genre, director, year, viewers) = listFilm[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvItemName.text = title
//        holder.binding.tvItemDescription.text = description
        holder.binding.tvItemGenre.text = genre
        holder.itemView.setOnClickListener {onItemClickCallback.onItemClicked(listFilm[holder.adapterPosition])}
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Film)
    }


}