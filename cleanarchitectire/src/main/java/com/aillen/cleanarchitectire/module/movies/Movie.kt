package com.aillen.cleanarchitectire.module.movies

import com.aillen.cleanarchitectire.core.extension.empty

/**
 * Created by anlonglong on 2018/6/28.
 * Email： 940752944@qq.com
 */
data class Movie(val id: Int, val poster: String): Fa() {

    companion object {
        fun empty() = Movie(0, String.empty())
    }
}

open class Fa