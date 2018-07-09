package com.aillen.cleanarchitectire.test

import javax.inject.Inject

/**
 * Created by anlonglong on 2018/7/6.
 * Email： 940752944@qq.com
 */
class MyPresenter @Inject constructor():BasePresenter() {
    override fun p() {
        println("-----MyPresenter------")
    }
}