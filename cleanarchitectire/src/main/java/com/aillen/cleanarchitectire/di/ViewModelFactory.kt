package com.aillen.cleanarchitectire.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.aillen.cleanarchitectire.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by anlonglong on 2018/6/29.
 * Email： 940752944@qq.com
 */
@Singleton
class ViewModelFactory @Inject constructor(private val creator:Map<Class<out ViewModel>,@JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        println("----------------------------")
        val creator = creator[modelClass] ?: creator.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("Unknown ViewModel class $modelClass")
        return try {creator.get() as T }catch (e:Exception){throw RuntimeException()}
    }
}


