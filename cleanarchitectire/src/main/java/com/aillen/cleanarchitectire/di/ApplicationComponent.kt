package com.aillen.cleanarchitectire.di

import com.aillen.cleanarchitectire.AndroidApplication
import com.aillen.cleanarchitectire.module.moviedetail.MovieDetailFragment
import com.aillen.cleanarchitectire.module.movies.MainActivity
import com.aillen.cleanarchitectire.module.movies.MovieFragment
import com.aillen.cleanarchitectire.test.PresenterModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by anlonglong on 2018/6/27.
 * Email： 940752944@qq.com
 */
@Singleton
@Component(modules =[ApplicationModule::class,ViewModeModule::class, PresenterModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(application: AndroidApplication)
    fun inject(movieFragment: MovieFragment)
    fun inject(movieDetailFragment: MovieDetailFragment)
}