package com.aillen.cleanarchitectire.module.movies

import com.aillen.cleanarchitectire.UserCase
import com.aillen.cleanarchitectire.repository.MoviesRepository
import javax.inject.Inject

/**
 * Created by anlonglong on 2018/6/28.
 * Email： 940752944@qq.com
 */

class GetMovies @Inject constructor(private val moviesRepository: MoviesRepository) : UserCase<List<Movie>,UserCase.None>(){
    override suspend fun run(params: None) = moviesRepository.movies()
}