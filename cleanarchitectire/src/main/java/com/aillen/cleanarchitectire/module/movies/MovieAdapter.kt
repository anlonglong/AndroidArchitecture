package com.aillen.cleanarchitectire.module.movies

import android.support.v4.view.ViewCompat
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.aillen.cleanarchitectire.R
import com.aillen.cleanarchitectire.R.id.moviePoster
import com.aillen.cleanarchitectire.core.extension.inflate
import com.aillen.cleanarchitectire.core.extension.loadFromUrl
import com.aillen.cleanarchitectire.module.movies.MovieAdapter.DiffCallback
import kotlinx.android.synthetic.main.row_movie.view.*
import javax.inject.Inject

/**
 * Created by anlonglong on 2018/6/29.
 * Email： 940752944@qq.com
 */
class MovieAdapter @Inject constructor() : ListAdapter<MovieView, MovieAdapter.MovieViewHolder>(DiffCallback) {
    object DiffCallback : DiffUtil.ItemCallback<MovieView>() {
        override fun areItemsTheSame(oldItem: MovieView, newItem: MovieView): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieView, newItem: MovieView): Boolean {
            return oldItem == newItem
        }
    }

    var onMovieItemClickListener:((MovieView,Int,View) ->Unit) = {movieView, i,imageView ->  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent.inflate(R.layout.row_movie))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieView = getItem(position)!!
        holder.itemView.moviePoster.loadFromUrl(movieView.poster)
        holder.itemView.setOnClickListener { onMovieItemClickListener(movieView,position,holder.getShareView()) }
    }

    inner class MovieViewHolder(private val item: View) : RecyclerView.ViewHolder(item){
        fun getShareView() = item.moviePoster
    }

}