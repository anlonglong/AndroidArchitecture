package com.aillen.cleanarchitectire.di

import android.content.Context
import com.aillen.cleanarchitectire.AndroidApplication
import com.aillen.cleanarchitectire.BuildConfig
import com.aillen.cleanarchitectire.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by anlonglong on 2018/6/27.
 * Email： 940752944@qq.com
 */

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides  @Singleton fun providerApplicationContext():Context = application

    @Provides @Singleton fun providerRetrofit() = Retrofit
            .Builder()
            .baseUrl("https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture-Kotlin/")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun createClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        }
        return builder.build()
    }


    @Provides @Singleton  fun providerMovieRepository(dataSource: MoviesRepository.Network): MoviesRepository = dataSource
}