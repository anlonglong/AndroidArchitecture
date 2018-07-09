package com.aillen.cleanarchitectire.test

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by anlonglong on 2018/7/6.
 * Email： 940752944@qq.com
 */
@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindBasePresenterFactory(factory: PresenterFactory): Factory


    @Binds
    @IntoMap
    @PresenterKey(MyPresenter::class)
    abstract fun bindPresenter(presenter: MyPresenter): BasePresenter
}