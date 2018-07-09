package com.aillen.cleanarchitectire.module.movies

import android.arch.lifecycle.MutableLiveData
import com.aillen.cleanarchitectire.UserCase
import com.aillen.cleanarchitectire.base.BaseViewModel
import com.aillen.cleanarchitectire.repository.MoviesRepository
import com.aillen.cleanarchitectire.repository.NetRequest
import com.aillen.cleanarchitectire.repository.NetworkHandler
import javax.inject.Inject

/**
 * Created by anlonglong on 2018/6/28.
 * Email： 940752944@qq.com
 *
 */
class MoviesViewModel @Inject constructor(private val getMovies:GetMovies):BaseViewModel() {

       var movies:MutableLiveData<List<MovieView>> = MutableLiveData()

       fun loadMovies() = getMovies.execute(UserCase.None()){
           it.either(::handleFailure,::handleMovieList)
       }

       private fun handleMovieList(movies:List<Movie>){
           this.movies.value = movies.map { MovieView(it.id,it.poster) }
       }
}