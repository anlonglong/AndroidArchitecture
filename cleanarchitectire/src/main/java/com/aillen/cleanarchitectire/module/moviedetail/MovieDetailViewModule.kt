package com.aillen.cleanarchitectire.module.moviedetail

import android.arch.lifecycle.MutableLiveData
import com.aillen.cleanarchitectire.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by anlonglong on 2018/7/6.
 * Email： 940752944@qq.com
 */
class MovieDetailViewModule @Inject constructor(private val getMovieDetail: GetMovieDetail):BaseViewModel() {

    val movieDetails = MutableLiveData<MovieDetails>()

    fun loadMovieDetails(movieId:Int){
        getMovieDetail.execute(GetMovieDetail.Params(movieId)){
            it.either(::handleFailure,::handleMovieDetails)
        }
    }

    private fun handleMovieDetails(movie: MovieDetails){
        movieDetails.value = movie
    }


}