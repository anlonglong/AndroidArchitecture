package com.aillen.cleanarchitectire.test

import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by anlonglong on 2018/7/6.
 * Email： 940752944@qq.com
 *
 * @JvmSuppressWildcards 这个注解必须加上，否则编译会出错
 */

@Singleton
class PresenterFactory @Inject
constructor(private val creator: Map<Class<out BasePresenter>,@JvmSuppressWildcards Provider<BasePresenter>>) : Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : BasePresenter> creator(pClazz: Class<T>): T {
        val provider = creator[pClazz] ?: creator.asIterable().firstOrNull {
            pClazz.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("Unknown ViewModel class： $pClazz")

        return provider.get() as T
    }
}