package com.aillen.cleanarchitectire.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.aillen.cleanarchitectire.functional.Either
import com.aillen.cleanarchitectire.exception.Failure
import com.aillen.cleanarchitectire.module.moviedetail.MovieDetails
import com.aillen.cleanarchitectire.module.movies.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.reflect.ParameterizedType
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by anlonglong on 2018/6/28.
 * Email： 940752944@qq.com
 *
 * 每个页面的网络请求可以写成如下的形式
 */

interface BaseRepository<out T> where T : Any {
    fun result(): Either<Failure, T>
}

class NetRequest<out T : Any> constructor(private val networkHandler: Boolean, private val call: Call<T>) : BaseRepository<T> {
    override fun  result(): Either<Failure, T> {
        return when (networkHandler) {
            true -> {

                doRequest(call)
            }
            false, null -> {
                Either.Left(Failure.NetworkConnection())
            }
        }
    }

    private  fun doRequest(call: Call<T>): Either<Failure, T> = try {
        val response = call.execute()
        when (response.isSuccessful) {
            true -> {
                Either.Right(response.body() ?:getNewInstance(call))
            }
            false -> {
                Either.Left(Failure.ServerError())
            }
        }
    } catch (e: Exception) {
        Either.Left(Failure.ServerError())
    }

    private  fun <T> getNewInstance(call: Call<T>): T {
        val java = call::class.java
        val type = (java.genericInterfaces[0] as ParameterizedType).actualTypeArguments[0]
        return type as T
    }


}
//-------------------------------------------------------------------------------------------
interface MoviesRepository {
    fun movies(): Either<Failure, List<Movie>>

    fun movieDetails(movieID:Int): Either<Failure, MovieDetails>

    class Network @Inject constructor(private val networkHandler: NetworkHandler,
                                      private val service: MoviesService) : MoviesRepository {
        override fun movieDetails(movieID: Int): Either<Failure, MovieDetails> {
            return when(networkHandler.isConnected) {
                true ->{
                    val response = service.movieDetails(movieID).execute()
                    if (response.isSuccessful) {
                        Either.Right(response.body()?:MovieDetails.empty())
                    }else{
                        Either.Left(Failure.ServerError())
                    }
                }
                false,null ->{
                    Either.Left(Failure.NetworkConnection())}
            }
        }

        override fun movies(): Either<Failure, List<Movie>> {
            return when (networkHandler.isConnected) {
                true -> {
                    request(service.movies(), {
                        it.map { it.toMovie() }
                    }, emptyList())
                }
                false, null -> {
                    Either.Left(Failure.NetworkConnection())
                }
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val execute = call.execute()
                when (execute.isSuccessful) {
                    true -> {
                        Either.Right(transform(execute.body() ?: default))
                    }
                    false -> {
                        Either.Left(Failure.ServerError())
                    }
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError())
            }
        }

    }
}

val Context.networkInfo: NetworkInfo?
    get() =
        (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

@Singleton
class NetworkHandler
@Inject constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnectedOrConnecting
}

@Singleton
class MoviesService
@Inject constructor(retrofit: Retrofit) : MoviesApi {
    override fun movieDetails(movieID: Int) = moviesApi.movieDetails(movieID)

    private val moviesApi by lazy { retrofit.create(MoviesApi::class.java) }

    override fun movies() = moviesApi.movies()

}

internal interface MoviesApi {
    companion object {
        private const val PARAM_MOVIE_ID = "movieId"
        private const val MOVIES = "movies.json"
        private const val MOVIE_DETAILS = "movie_0{$PARAM_MOVIE_ID}.json"
    }

    @GET(MOVIES)
    fun movies(): Call<List<MovieEntity>>
    @GET(MOVIE_DETAILS)
    fun movieDetails(@Path(PARAM_MOVIE_ID) movieID: Int): Call<MovieDetails>
}

data class MovieEntity(private val id: Int, private val poster: String) {
    fun toMovie() = Movie(id, poster)
}