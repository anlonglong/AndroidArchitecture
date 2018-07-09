package com.aillen.cleanarchitectire.module.moviedetail

import com.aillen.cleanarchitectire.functional.Either
import com.aillen.cleanarchitectire.UserCase
import com.aillen.cleanarchitectire.exception.Failure
import com.aillen.cleanarchitectire.repository.MoviesRepository
import javax.inject.Inject

/**
 * Created by anlonglong on 2018/7/6.
 * Email： 940752944@qq.com
 */

class GetMovieDetail @Inject constructor(private val repository: MoviesRepository) : UserCase<MovieDetails, GetMovieDetail.Params>() {
    override suspend fun run(params: Params): Either<Failure, MovieDetails> = repository.movieDetails(params.movieId)
    data class Params(val movieId:Int)
}