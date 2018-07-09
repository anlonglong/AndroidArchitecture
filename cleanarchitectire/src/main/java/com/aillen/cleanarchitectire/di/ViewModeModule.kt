package com.aillen.cleanarchitectire.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.aillen.cleanarchitectire.annotation.ViewModelKey
import com.aillen.cleanarchitectire.module.moviedetail.MovieDetailViewModule
import com.aillen.cleanarchitectire.module.movies.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by anlonglong on 2018/6/28.
 * Email： 940752944@qq.com
 */
@Module
abstract class ViewModeModule {

    @Binds //Bind注解一般用来绑定接口或者抽象类的，返回值类型是接口[用来限定边界]，参数类型是接口的实现类
    internal abstract fun bindViewModuleFactory(factory:ViewModelFactory):ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(moviesViewModel: MoviesViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModule::class)
    abstract fun bindMoviesDetailViewModel(moviesViewModel: MovieDetailViewModule):ViewModel
}