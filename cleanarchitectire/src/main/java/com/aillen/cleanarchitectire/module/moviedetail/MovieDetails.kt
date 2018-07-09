package com.aillen.cleanarchitectire.module.moviedetail

import com.aillen.cleanarchitectire.core.extension.empty

/**
 * Created by anlonglong on 2018/7/6.
 * Email： 940752944@qq.com
 */
data class MovieDetails(val id: Int,
                        val title: String,
                        val poster: String,
                        val summary: String,
                        val cast: String,
                        val director: String,
                        val year: Int,
                        val trailer: String) {

    companion object {
        fun empty() = MovieDetails(0, String.empty(), String.empty(), String.empty(),
                String.empty(), String.empty(), 0, String.empty())
    }
}