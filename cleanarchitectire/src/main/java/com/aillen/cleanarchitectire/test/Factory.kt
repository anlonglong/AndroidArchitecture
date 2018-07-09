package com.aillen.cleanarchitectire.test

/**
 * Created by anlonglong on 2018/7/6.
 * Email： 940752944@qq.com
 */
interface Factory {

    fun <T : BasePresenter> creator(pClazz:Class<T>):T

}